package com.watson.auth.socialauth.service;

import com.watson.auth.socialauth.dto.UserPrincipalDetails;
import com.watson.auth.user.domain.entity.User;
import com.watson.auth.user.domain.repository.UserRepository;
import com.watson.auth.user.dto.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
@Component
public class UserPrincipalOauth2UserService extends DefaultOAuth2UserService {

    Scanner sc = new Scanner(System.in); // 회원 가입 중 닉네임 입력 받음

    @Autowired // config에서 Bean 등록
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${spring.security.code}")
    String code;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("getClientRegistration : " + userRequest.getClientRegistration());
        log.info("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
        log.info("getAttributes : " + super.loadUser(userRequest).getAttributes());

        /* 회원인지 확인 후 회원 가입 */
        OAuth2User oauth2User = super.loadUser(userRequest);

        /* 1. 이미 회원인지 확인. authId로 */
        /* 1-1. authId 추출 */
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = Long.toString(oauth2User.getAttribute("id"));
        String authId = provider + "_" + providerId;
        log.info("authId : " + authId);

        /* 1-2. authId로 기회원인지 확인 (일단 User 테이블만 확인하고 이후에 Realtor도 확인하도록 추가) */
        User loginUser = userRepository.findByAuthId(authId);
        // newUser가 null이면 realtor도 검색

        /* 기회원 유무에 따라 회원가입/로그인 진행 */
        String nickname = "";
        String password = bCryptPasswordEncoder.encode(code); // security 사용을 위해 pw 등록
        String role = "ROLE_USER";

        if (loginUser == null) {
            /* 2. 회원가입 진행 */
            // user인지, realtor인지는 프론트에서 넘겨줌
            log.info("회원가입을 진행합니다.");

            /* 2-1. id 14자리 난수 생성(role + 12자리 Long) (Users 테이블 PK) */
            String id = "";
            while (true) {
                String tmpId = "u_" + Long.toString((long) ((Math.random() * (Math.pow(10, 11) - 11111111111L)) + Math.pow(10, 11)));

                User findId = userRepository.findById(tmpId);
                if (findId == null) {
                    id = tmpId;
                    break;
                }
            }

            /* 2-2. 닉네임 설정 */
            while (true) {
                log.info("닉네임 입력 : ");
                String tmpNickname = sc.next();

                /* 중복 닉네임 검사 */
                User findNickname = userRepository.findByNickname(tmpNickname);
                if (findNickname == null) {
                    log.info("사용 가능한 닉네임 입니다."); // 닉네임 유효성 검사??
                    nickname = tmpNickname;
                    break;
                } else {
                    log.info("사용 중인 닉네임 입니다. 다른 닉네임을 입력해주세요.");
                }
            }

            /* 2-3. 회원가입 시킬 유저 생성 */
            loginUser = User.builder()
                    .id(id)
                    .nickname(nickname)
                    .password(password)
                    .authId(authId)
                    .authType(userRequest.getClientRegistration().getRegistrationId())
                    .role(role)
                    .accessToken(userRequest.getAccessToken().getTokenValue())
                    .vapKey("vapKey") // vapKey는 클라이언트에서 받아옴
                    .build();

            /* 2-4. DB에 저장(회원가입) */
            userRepository.save(loginUser); // 회원가입

        }

        return new UserPrincipalDetails(loginUser, oauth2User.getAttributes());
    }
}