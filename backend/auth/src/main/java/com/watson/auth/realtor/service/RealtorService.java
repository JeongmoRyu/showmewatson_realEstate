package com.watson.auth.realtor.service;

import com.watson.auth.admin.dto.RealtorLoginResponse;
import com.watson.auth.admin.jwt.JwtTokens;
import com.watson.auth.admin.jwt.JwtUtils;
import com.watson.auth.realtor.domain.entity.Agency;
import com.watson.auth.realtor.domain.entity.Realtor;
import com.watson.auth.realtor.domain.repository.RealtorRepository;
import com.watson.auth.realtor.dto.RealtorSignupRequest;
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
    private final AgencyService agencyService;
    private final RealtorImageService realtorImageService;

    public Realtor findRealtorByAuthId(String authId) {
        return realtorRepository.findByAuthId(authId);
    }

    public RealtorLoginResponse modifyAccessToken(Realtor realtor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RealtorLoginResponse realtorLoginResponse = new RealtorLoginResponse();

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

            // DB에 Access Token 저장
            realtor.setAccessToken(accessToken); // 새로운 accessToken으로 변경
            realtorRepository.save(realtor);

            realtorLoginResponse = RealtorLoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .role("User")
                    .build();
        }

        return realtorLoginResponse;
    }

//    public Realtor addRealtor(MultipartFile file, RealtorSignupRequest realtorSignupRequest) {
    public Realtor addRealtor(RealtorSignupRequest realtorSignupRequest) {

        log.info("중개사 회원가입을 진행합니다.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /* 1. id 14자리 난수 생성(role + 12자리 Long) (Realtors 테이블 PK) */
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
        String providerId = authentication.getName(); // kakao에서 주는 id 값
        String authId = "kakao_" + providerId; // authentication에서 provider name 받아올 수 있는지 확인
        log.info("authId : " + authId);

        /* 3. agencyId로 agency 찾기  */
//        String registId = realtorSignupRequest.getAgency().getRegistId();
//        Agency findAgency = agencyService.findAgencyByRegistId(registId);
//        if (findAgency == null) {
//            Agency newAgency = Agency.builder()
//                    .registId(registId)
//                    .agencyName(realtorSignupRequest.getAgency().getAgencyName())
//                    .address(realtorSignupRequest.getAgency().getAddress())
//                    .tel(realtorSignupRequest.getAgency().getTel())
//                    .agencyImg(realtorSignupRequest.getAgency().getAgencyImg())
//                    .build();
//            agencyService.addAgency(newAgency);
//            findAgency = agencyService.findAgencyByRegistId(registId);
//        }
//        log.info("agency : " + findAgency.toString());

//        /* 4. profileImg 이름 생성하기 */
//        String profileImg = createFileName(file.getOriginalFilename(), "realtor");
//        log.info("profileImg : " + profileImg);
//
//        realtorImageService.addRealtorImage(file, "realtor"); // S3 안에 폴더명

        /* 5. 회원가입 시킬 Realtor를 만들기 */
        return Realtor.builder()
                .id(id) // PK
                .authType("Kakao")
                .authId(authId)
//                .agency(findAgency) // 사무소ID
                .license(realtorSignupRequest.getLicense())
                .realtorName(realtorSignupRequest.getRealtorName())
                .phoneNumber(realtorSignupRequest.getPhoneNumber())
                .role("Realtor")
                .accessToken("tmpAccessToken") // 회원가입 후 로그인하면서 업데이트될 값
                .password(bCryptPasswordEncoder.encode(code))
                .vapKey("vapKey") // vapKey는 클라이언트에서 받아옴
//                .profileImg(profileImg) // 이미지 이름 저장
                .build();

    }

    /* 파일 업로드 메소드 */
    public String createFileName(String fileName, String dirName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌림
        return dirName + "/" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    public String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

}