package com.watson.auth.user.controller;

import com.watson.auth.jwt.*;
import com.watson.auth.socialauth.service.UserPrincipalOauth2UserService;
import com.watson.auth.user.domain.entity.User;
import com.watson.auth.user.dto.UserLoginResponse;
import com.watson.auth.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "User 컨트롤러")
@RequestMapping(value = "/auth/user")
public class UserController {

    private final JwtUtils jwtUtils;

    public UserController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private UserService userService;

    @Autowired
    UserPrincipalOauth2UserService userPrincipalOauth2UserService;

    /* 로그인 : 기회원 확인 및 미회원인 경우 회원가입까지 된 상태 */
    @GetMapping("/login")
    public ResponseEntity<UserLoginResponse> userLogin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // authentication 가져오기
        log.info("authId : " + authentication.getName()); // authId 가져옴

        UserLoginResponse userLoginResponse = new UserLoginResponse(); // 리턴할 userLogingResponse

        // 인증 성공 시 Access Token과 Refresh Token 생성
        if(authentication.isAuthenticated()) {
            JwtTokens jwtTokens = jwtUtils.generateTokens(authentication);

            userLoginResponse = UserLoginResponse.builder()
                    .accessToken(jwtTokens.getAccessToken())
                    .refreshToken(jwtTokens.getRefreshToken())
                    .build();

            String accessToken = userLoginResponse.getAccessToken();
            String refreshToken = userLoginResponse.getRefreshToken();

            log.info("AccessToken : " + accessToken);
            log.info("RefreshToken : " + refreshToken);

            // DB에 Access Token 저장
            String authId = authentication.getName();
            User loginUser = userService.findUserByAuthId(authId);
            loginUser.setAccessToken(accessToken); // 새로운 accessToken으로 변경
            userService.modifyAccessToken(loginUser);

            return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userLoginResponse);

    }

    /* 정보 수정 */


    /* 탈퇴 */


    /* 회원 정보 조회 */


}
