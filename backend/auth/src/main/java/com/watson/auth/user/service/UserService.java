package com.watson.auth.user.service;

import com.watson.auth.admin.dto.UserLoginResponse;
import com.watson.auth.admin.jwt.JwtTokens;
import com.watson.auth.admin.jwt.JwtUtils;
import com.watson.auth.admin.service.RedisService;
import com.watson.auth.user.domain.entity.User;
import com.watson.auth.user.domain.repository.UserRepository;
import com.watson.auth.user.dto.UserLoginRequest;
import com.watson.auth.user.dto.UserResponse;
import com.watson.auth.user.dto.UserSignupResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RedisService redisService;

    @Autowired // config에서 Bean 등록
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.security.code}")
    String code;

    @Value("${jwt.secret}")
    private String secretKey;

    static String provider = "kakao";

    public boolean validateToken(String accessToken) {
        String authId = jwtUtils.getAuthIdByAccessToken(accessToken);
        log.info("authId : " + authId);

        User findUser = userRepository.findByAuthId(authId);
        log.info("findUser : " + findUser.toString());

        if (accessToken.equals(findUser.getAccessToken())) {
            /* 유효한 토큰인가? */
            if (jwtUtils.validateToken(accessToken)) {
                return true;
            } else {
                /* 3. 토큰이 유효하지 않다면 -> Refresh Token은 유효한가? */
                String refreshToken = redisService.getValues(authId);
                log.info("refreshToken : " + refreshToken);

                if(jwtUtils.validateToken(refreshToken)) {
                    /* 4. 유혀한 경우 -> Refresh Token으로 Access Token 재발급 및 Access Token 업데이트 */
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
                    String newAccessToken = jwtUtils.generateAccessToken(oauthToken);

                    findUser.setAccessToken(newAccessToken); // 프론트에 newAccessToken 전달해줘야 함
                    userRepository.save(findUser);

                    return true;
                }
            }
        }
        return false;
    }

    public UserLoginResponse modifyAccessToken(UserLoginRequest userLoginRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        User loginUser = userRepository.findByAuthId(userLoginRequest.getAuthId());

        /* 사용자면 사용자로 로그인 */
        log.info("사용자로 로그인을 진행합니다.");
        log.info("로그인 토큰을 발급합니다.");
        if (authentication.isAuthenticated()) {

            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            log.info("oauthToken : " + oauthToken.toString());

            JwtTokens jwtTokens = jwtUtils.generateTokens(oauthToken);

            String accessToken = jwtTokens.getAccessToken();
            String refreshToken = jwtTokens.getRefreshToken();

            log.info("AccessToken : " + accessToken);
            log.info("RefreshToken : " + refreshToken);

            // DB에 Access Token 저장
            loginUser.setAccessToken(accessToken); // 새로운 accessToken으로 변경
            userRepository.save(loginUser);

            // Redis에 Refresh Token 저장
            redisService.setValues(loginUser.getAuthId(), refreshToken);

            log.info("사용자 로그인이 완료되었습니다.");

            userLoginResponse = UserLoginResponse.builder()
                    .accessToken(accessToken)
                    .role("User")
                    .build();
        }

        return userLoginResponse;
    }

    public User findUserByAuthId(String authId) {
        return userRepository.findByAuthId(authId);
    }

    public UserSignupResponse addUser(String nickname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("사용자 회원가입을 진행합니다.");

        /* 1. id 14자리 난수 생성(role + 12자리 Long) (Users 테이블 PK) */
        String id = "";
        while (true) {
            String tmpId = "u_" + Long.toString((long) ((Math.random() * (Math.pow(10, 11) - 11111111111L)) + Math.pow(10, 11)));

            User findId = userRepository.findByAuthId(tmpId);
            if (findId == null) {
                id = tmpId;
                break;
            }
        }
        log.info("id : " + id);

        /* 2. authId 생성 */
        String providerId = authentication.getName(); // kakao에서 주는 id 값
        String authId = provider + "_" + providerId; // authentication에서 provider name 받아올 수 있는지 확인
        log.info("authId : " + authId);

        /* 3. 회원가입 시킬 UserSignupResponse를 만들기 */
        User newUser = User.builder()
                .id(id)
                .nickname(nickname)
                .authId(authId)
                .authType("Kakao")
                .role("User")
                .accessToken("tmpAccessToken") // 회원가입 후 로그인하면서 업데이트될 값
                .password(bCryptPasswordEncoder.encode(code)) // security 사용을 위해 pw 등록)
                .vapKey("vapKey") // vapKey는 클라이언트에서 받아옴
                .build();

        /* 4. 회원가입 (DB 저장) */
        userRepository.save(newUser);

        return UserSignupResponse.builder()
                .authId(newUser.getAuthId())
                .authType(newUser.getAuthType())
                .build();
    }

    public User findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public UserResponse findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authId = provider + "_" + authentication.getName(); // authId 하드코딩해서 테스트 완료(kakao_2941928004)

        User findUser = userRepository.findByAuthId(authId);
        UserResponse userResponse = new UserResponse();
        userResponse.setNickname(findUser.getNickname());

        return userResponse;
    }

    public String findNicknameByAccessToken(String accessToken) {
        log.info("accessToken : " + accessToken);
        String authId = jwtUtils.getAuthIdByAccessToken(accessToken);
        log.info("authId : " + authId);
        User findUser = userRepository.findByAuthId(authId);
        log.info("findUser : " + findUser.toString());

        return findUser.getNickname();
    }

    public void modifyNickname(String accessToken, String nickname) {
        String authId = jwtUtils.getAuthIdByAccessToken(accessToken);
        User findUser = userRepository.findByAuthId(authId);

        findUser.setNickname(nickname);
        userRepository.save(findUser);
    }
}