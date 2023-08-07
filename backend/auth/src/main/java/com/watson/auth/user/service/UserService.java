package com.watson.auth.user.service;

import com.watson.auth.admin.dto.UserLoginResponse;
import com.watson.auth.admin.jwt.JwtTokens;
import com.watson.auth.admin.jwt.JwtUtils;
import com.watson.auth.user.domain.entity.User;
import com.watson.auth.user.domain.repository.UserRepository;
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

    @Autowired // config에서 Bean 등록
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.security.code}")
    String code;

    public UserLoginResponse modifyAccessToken(User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLoginResponse userLoginResponse = new UserLoginResponse();

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
            user.setAccessToken(accessToken); // 새로운 accessToken으로 변경
            userRepository.save(user);

            userLoginResponse = UserLoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .role("User")
                    .build();
        }

        return userLoginResponse;
    }

    public User findUserByAuthId(String authId) {
        return userRepository.findByAuthId(authId);
    }

    public User addUser(String nickname) {
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
        String authId = "kakao_" + providerId; // authentication에서 provider name 받아올 수 있는지 확인
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

        return newUser;
    }

    public User findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

}