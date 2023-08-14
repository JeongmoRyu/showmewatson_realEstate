package com.watson.auth.realtor.service;

import com.watson.auth.admin.dto.RealtorLoginResponse;
import com.watson.auth.admin.jwt.JwtTokens;
import com.watson.auth.admin.jwt.JwtUtils;
import com.watson.auth.admin.service.RedisService;
import com.watson.auth.realtor.domain.entity.Realtor;
import com.watson.auth.realtor.domain.repository.RealtorRepository;
import com.watson.auth.realtor.dto.*;
import com.watson.auth.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RealtorService {

    @Autowired // config에서 Bean 등록
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.security.code}")
    String code;

    private final JwtUtils jwtUtils;
    private final RealtorRepository realtorRepository;
    private final RealtorImageService realtorImageService;
    private final RedisService redisService;

    // accessToken 유효 -> realtorId만 반환
    // refeshToken으로 accessToken 재발급 -> realtorId, accessToken 반환
    // 그 외 -> null 반환
    public Map<String, String> validateToken(String accessToken) { // return "userId": userId, "accessToken":accessToken
        String authId = jwtUtils.getAuthIdByAccessToken(accessToken);
        log.info("authId : " + authId);

        String[] redisTokens = redisService.getValues(authId).split(",");
        String redisAccessToken = redisTokens[0];

        if (accessToken.equals(redisAccessToken)) {

            Map<String, String> validationResults = new HashMap<>();
            Realtor findRealtor = realtorRepository.findByAuthId(authId);// authId로 userId 가져오기
            validationResults.put("userId", findRealtor.getId());

            /* 유효한 토큰인가? */
            if (!jwtUtils.validateToken(accessToken)) {
                log.info("AccessToken이 유효하지 않습니다.");
                /* 토큰이 유효하지 않다면 -> Refresh Token은 유효한가? */
                String refreshToken = redisTokens[1];
                log.info("refreshToken : " + refreshToken);

                if(jwtUtils.validateToken(refreshToken)) {
                    log.info("RefreshToken이 유효합니다.");
                    /* 유효한 경우 -> Refresh Token으로 Access Token 재발급 및 Access Token 업데이트 */
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                    String newAccessToken = jwtUtils.generateAccessToken(oauthToken);
                    String newTokens = newAccessToken + "," + refreshToken;
                    redisService.updateValues(authId, newTokens); // redis에 새로운 토큰으로 변경

                    // userId, 프론트에 전달할 새로운 accessToken 전달
                    validationResults.put("accessToken", newAccessToken);
                } else {
                    // refreshToken도 유효하지 않다면
                    return null;
                }
                return validationResults;
            }
        }
        return null; // token 불일치
    }

    public Realtor findRealtorByAuthId(String authId) {
        return realtorRepository.findByAuthId(authId);
    }

    public RealtorLoginResponse modifyAccessToken(RealtorLoginRequest realtorLoginRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RealtorLoginResponse realtorLoginResponse = new RealtorLoginResponse();

        Realtor loginRealtor = realtorRepository.findByAuthId(realtorLoginRequest.getAuthId());

        log.info("중개사로 로그인을 진행합니다.");

        log.info("로그인 토큰을 발급합니다.");
        if (authentication.isAuthenticated()) {

            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            log.info("oauthToken : " + oauthToken);

            JwtTokens jwtTokens = jwtUtils.generateTokens(oauthToken);

            String accessToken = jwtTokens.getAccessToken();
            String refreshToken = jwtTokens.getRefreshToken();

            log.info("AccessToken : " + accessToken);
            log.info("RefreshToken : " + refreshToken);

            String tokens = accessToken + "," + refreshToken;

            // Redis에 Refresh Token 저장
            redisService.setValues(loginRealtor.getAuthId(), tokens);

            log.info("중개사 로그인이 완료되었습니다.");

            return RealtorLoginResponse.builder()
                    .accessToken(accessToken)
                    .role("User")
                    .build();
        }

        return realtorLoginResponse;
    }

    public RealtorLoginRequest addRealtor(MultipartFile profileImg, MultipartFile agencyImg, RealtorSignupRequest realtorSignupRequest) {

        log.info("중개사 회원가입을 진행합니다.");

        /* 1. id 14자리 난수 생성(role + 12자리 Long) (Realtors 테이블 PK) */
        log.info("id를 생성합니다.");
        String id = "";
        while (true) {
            String tmpId = "r_" + (long) ((Math.random() * (Math.pow(10, 11) - 11111111111L)) + Math.pow(10, 11));

            Realtor findId = realtorRepository.findById(tmpId);
            if (findId == null) {
                id = tmpId;
                break;
            }
        }
        log.info("id : " + id);

        /* 2. authId 생성 */
        log.info("authId를 가져옵니다.");
        String authId = realtorSignupRequest.getAuthId();
        log.info("authId : " + authId);

        /* 3. profileImg 이름 생성 및 등록 */
        String dirName = "realtor";

        String profileImgName = createFileName(profileImg.getOriginalFilename(), dirName);
        log.info("profileImgName : " + profileImgName);

        realtorImageService.addRealtorImage(profileImg, dirName); // S3 안에 폴더명

        /* 4. agencyImg 이름 생성 및 등록 */
        String agencyImgName = createFileName(agencyImg.getOriginalFilename(), "agency");
        log.info("agencyImgName : " + agencyImgName);

        realtorImageService.addRealtorImage(profileImg, dirName); // S3 안에 폴더명

        /* 5. 회원가입 시킬 Realtor를 만들기 */
        Realtor newRealtor = Realtor.builder()
                .id(id) // PK
                .authType(realtorSignupRequest.getAuthType())
                .authId(authId)
                .license(realtorSignupRequest.getLicense())
                .realtorName(realtorSignupRequest.getRealtorName())
                .phoneNumber(realtorSignupRequest.getPhoneNumber())
                .role("Realtor")
                .password(bCryptPasswordEncoder.encode(code))
                .profileImg(profileImgName) // 중개사 프로필 이미지
                .registId(realtorSignupRequest.getRegistId())
                .agencyName(realtorSignupRequest.getAgencyName())
                .address(realtorSignupRequest.getAddress())
                .tel(realtorSignupRequest.getTel())
                .agencyImg(agencyImgName) // 사무소 이미지
                .fcmToken(realtorSignupRequest.getFcmToken())
                .build();
        log.info("newRealtor : " + newRealtor.toString());

        realtorRepository.save(newRealtor);
        log.info("중개사 회원가입이 완료되었습니다.");

        return RealtorLoginRequest.builder()
                .authId(newRealtor.getAuthId())
                .authType(newRealtor.getAuthType())
                .build();
    }

    /* 파일 업로드 메소드 */
    public String createFileName(String fileName, String dirName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌림
        String createdFileName = dirName + "/" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
        log.info("createdFileName : " + createdFileName);
        return createdFileName;
    }

    public String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    public RealtorResponse findRealtorByLicense(String license) {
        Realtor findRealtor = realtorRepository.findByLicense(license);
        return RealtorResponse.builder()
                .realtorName(findRealtor.getRealtorName())
                .phoneNumber(findRealtor.getPhoneNumber())
                .profileImg(findRealtor.getProfileImg())
                .registId(findRealtor.getRegistId())
                .agencyName(findRealtor.getAgencyName())
                .build();
    }

    public AgencyResponse findAgencyByRegistId(String registId) {
        Realtor findRealtor = realtorRepository.findByRegistId(registId);

        /* 공인중개사 ID로 매물 찾기 */
        /**
         * String realtorId = findRealtor.getId();
         * List<HouseResponses> findHouses = houseService.findHousesByRealtorId(realtorId);
         */

        return AgencyResponse.builder()
                .agencyName(findRealtor.getAgencyName())
                .address(findRealtor.getAddress())
                .tel(findRealtor.getTel())
                .realtorId(findRealtor.getId())
                .realtorName(findRealtor.getRealtorName())
//                .houseId(findHouses)
                .build();
    }
}