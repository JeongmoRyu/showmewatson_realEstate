package com.watson.auth.admin.controller;

import com.watson.auth.realtor.domain.entity.Realtor;
import com.watson.auth.admin.dto.RealtorLoginResponse;
import com.watson.auth.realtor.dto.RealtorLoginRequest;
import com.watson.auth.realtor.dto.RealtorSignupRequest;
import com.watson.auth.realtor.service.RealtorService;
import com.watson.auth.user.domain.entity.User;
import com.watson.auth.admin.dto.UserLoginResponse;
import com.watson.auth.user.dto.UserLoginRequest;
import com.watson.auth.user.dto.UserSignupRequest;
import com.watson.auth.user.dto.UserSignupResponse;
import com.watson.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/auth/admin")
@Controller // html 페이지 이동을 위해 추가
public class AdminController {

    @GetMapping("/test")
    public @ResponseBody String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken) {
            return authentication.toString();
        }
        return "test";
    }

    private final UserService userService;
    private final RealtorService realtorService;

    @Value("${spring.security.code}")
    String code;

    /* 회원인지 확인 */
    /* 회원이면 -> User/Realtor에 맞게 로그인 */
    /* 회원이 아니면 -> User/Realtor 선택 후 회원가입 */
    @GetMapping
    public String checkRegisteration() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("회원여부를 확인합니다.");

        /* 1. 이미 회원인지 확인. authId로 */
        /* 1-1. authId 추출 */
        String providerId = authentication.getName(); // kakao에서 주는 id 값
        String authId = "kakao_" + providerId; // authentication에서 provider name 받아올 수 있는지 확인
        log.info("authId : " + authId);

        /* 1-2. authId로 기회원인지 확인 */

        User loginUser = userService.findUserByAuthId(authId);
        /* 1-3. 회원이면 로그인 */
        if (loginUser != null) { // 사용자면 사용자로 로그인
            UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                    .authId(loginUser.getAuthId())
                    .authType(loginUser.getAuthType())
                    .build();
            userLogin(userLoginRequest);
            return null;
        } else { // 중개사면 중개사로 로그인
            Realtor loginRealtor = realtorService.findRealtorByAuthId(authId);
            if (loginRealtor != null) {
                RealtorLoginRequest realtorLoginRequest = RealtorLoginRequest.builder()
                        .authId(loginRealtor.getAuthId())
                        .authType(loginRealtor.getAuthType())
                        .build();
                realtorLogin(realtorLoginRequest);
                return null;
            } else {
                /* 1-4. 회원이 아니면 Role 선택 후 회원가입 */
                return "signupRoleOption";
            }
        }
    }

    public ResponseEntity<UserLoginResponse> userLogin(UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = userService.modifyAccessToken(userLoginRequest);

        if(userLoginResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userLoginResponse);
        }
    }

    public ResponseEntity<RealtorLoginResponse> realtorLogin(RealtorLoginRequest realtorLoginRequest) {

        RealtorLoginResponse realtorLoginResponse = realtorService.modifyAccessToken(realtorLoginRequest);

        if(realtorLoginResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(realtorLoginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(realtorLoginResponse);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<UserLoginResponse> userSignup(String nickname) {

        while(true) { // 닉네임 중복 체크
            User findUser = userService.findUserByNickname(nickname);
            if(findUser == null) {
                log.info("사용 가능한 닉네임입니다.");
                break;
            } else {
                log.info("사용 중인 닉네임입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 이미 사용 중인 닉네임
            }
        }

        UserSignupResponse newUser = userService.addUser(nickname); // 회원가입

        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .authId(newUser.getAuthId())
                .authType(newUser.getAuthType())
                .build();

        return userLogin(userLoginRequest);
    }

    @PostMapping("/realtor")
    public ResponseEntity<RealtorLoginResponse> realtorSignup(@RequestPart MultipartFile profileImg, @RequestPart MultipartFile agencyImg, @RequestPart RealtorSignupRequest realtorSignupRequest) {

        RealtorLoginRequest realtorLoginRequest = realtorService.addRealtor(profileImg, agencyImg, realtorSignupRequest); // 회원가입

        return realtorLogin(realtorLoginRequest);
    }

}