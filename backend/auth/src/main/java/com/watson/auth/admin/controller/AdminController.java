package com.watson.auth.admin.controller;

import com.watson.auth.admin.dto.LoginResponse;
import com.watson.auth.admin.jwt.JwtUtils;
import com.watson.auth.realtor.domain.entity.Realtor;
import com.watson.auth.admin.dto.RealtorLoginResponse;
import com.watson.auth.realtor.dto.RealtorLoginRequest;
import com.watson.auth.realtor.dto.RealtorSignupRequest;
import com.watson.auth.realtor.service.RealtorService;
import com.watson.auth.user.domain.entity.User;
import com.watson.auth.admin.dto.UserLoginResponse;
import com.watson.auth.user.dto.UserCheckNicknameRequest;
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

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/auth/admin")
@RestController
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
    private final JwtUtils jwtUtils;

//    @Value("${spring.security.code}")
//    String code;

    /* 회원인지 확인 */
    /* 회원이면 -> User/Realtor에 맞게 로그인 */
    /* 회원이 아니면 -> User/Realtor 선택 후 회원가입 */
    /* UUID로 AuthId를 사용하고, 클라에 AuthId(UUID)를 반환 */
    @GetMapping("/check-regist/{authId}")
    public ResponseEntity<String> checkRegisteration(@PathVariable String authId) { // 중개사/유저 선택 페이지로 이동 때문에 return String

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("회원여부를 확인합니다.");

//        /* 1. 이미 회원인지 확인. authId로 */
//        /* 1-1. authId 추출 */
//        String providerId = authentication.getName(); // kakao에서 주는 id 값
//        String authId = "kakao_" + providerId; // authentication에서 provider name 받아올 수 있는지 확인
        log.info("authId : " + authId);

        /* 1-2. authId로 기회원인지 확인 */

        User loginUser = userService.findUserByAuthId(authId);
        /* 1-3. 회원이면 로그인 */
        if (loginUser != null) { // 사용자면 사용자로 로그인
//            if (loginUser.getAuthId().equals("kakao_" + authentication.getName())) { // 동일 사용자 맞으면 로그인
            if (loginUser.getAuthId().equals(authId)) { // 동일 사용자 맞으면 로그인
                UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                        .authId(loginUser.getAuthId())
                        .build();
                userLogin(userLoginRequest);

                log.info("UUID : " + loginUser.getAuthId());
                return ResponseEntity.status(HttpStatus.OK).body(loginUser.getAuthId());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 다른 유저
            }
        } else { // 중개사면 중개사로 로그인
            Realtor loginRealtor = realtorService.findRealtorByAuthId(authId);
            if (loginRealtor != null) {
//                if (loginRealtor.getAuthId().equals("kakao_" + authentication.getName())) { // 동일 사용자면 로그인
                if (loginRealtor.getAuthId().equals(authId)) { // 동일 사용자면 로그인
                    RealtorLoginRequest realtorLoginRequest = RealtorLoginRequest.builder()
                            .authId(loginRealtor.getAuthId())
                            .authType(loginRealtor.getAuthType())
                            .build();
                    realtorLogin(realtorLoginRequest);
                    return ResponseEntity.status(HttpStatus.OK).body(loginRealtor.getAuthId());
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 다른 유저
                }
            } else {
                /* 1-4. 회원이 아니면 Role 선택 후 회원가입 */
                return ResponseEntity.status(HttpStatus.OK).body(authId);
            }
        }
    }

    @GetMapping("/user-login")
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

    @GetMapping("/user-nickname")
    public ResponseEntity<String> checkNickname(UserCheckNicknameRequest userCheckNicknameRequest) {
        String nickname = userCheckNicknameRequest.getNickname();
        User findUser = userService.findUserByNickname(nickname);
        if(findUser == null) {
            log.info("사용 가능한 닉네임입니다.");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            log.info("사용 중인 닉네임입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 이미 사용 중인 닉네임
        }
    }

    @PostMapping("/user")
    public ResponseEntity<UserLoginResponse> userSignup(@RequestPart UserSignupRequest userSignupRequest) {
        try {
            /* 1. userSignupRequest 보낸 사용자와 카카오 로그인 한 사용자가 동일한가? */
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            log.info("authId : " + userSignupRequest.getAuthId());
            if (userSignupRequest.getAuthId().equals("kakao_" + authentication.getName())) {
                /* 닉네임 중복 체크 */
                UserCheckNicknameRequest userCheckNicknameRequest = UserCheckNicknameRequest.builder()
                        .nickname(userSignupRequest.getNickname())
                        .build();

                HttpStatus nicknameCheckResponse = checkNickname(userCheckNicknameRequest).getStatusCode();

                if (nicknameCheckResponse.is2xxSuccessful()) { // 사용 가능한 닉네임
                    UserSignupResponse newUser = userService.addUser(userSignupRequest); // 회원가입

                    UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                            .authId(newUser.getAuthId())
                            .build();

                    return userLogin(userLoginRequest);
                } else {
                    return ResponseEntity.status(nicknameCheckResponse).build(); // 중복 닉네임
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 다른 유저
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/realtor")
    public ResponseEntity<RealtorLoginResponse> realtorSignup(@RequestPart MultipartFile agencyImg, @RequestPart RealtorSignupRequest realtorSignupRequest) {
        try {
            /* 1. userSignupRequest 보낸 사람과 카카오 로그인 한 사람이 동일한가? */
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("authId : " + realtorSignupRequest.getAuthId());

            if (realtorSignupRequest.getAuthId().equals("kakao_" + authentication.getName())) {
                RealtorLoginRequest realtorLoginRequest = realtorService.addRealtor(agencyImg, realtorSignupRequest); // 회원가입
                return realtorLogin(realtorLoginRequest);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 다른 유저
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* accessToken -> userId */
    @GetMapping("/get-userid/{accessToken}")
    public ResponseEntity<String> getUserId(@PathVariable String accessToken) {
        try {
            Map<String, String> validationResults = userService.validateToken(accessToken);
            if (validationResults != null) { // user인 경우
                return ResponseEntity.status(HttpStatus.OK).body(validationResults.get("userId"));
            } else {
                log.info("중개사인지 확인합니다.");
                validationResults = realtorService.validateToken(accessToken);
                if (validationResults != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(validationResults.get("realtorId"));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* accessToken -> fcmToken */
    @GetMapping("/get-fcm-token/{accessToken}")
    public ResponseEntity<String> getFcmToken(@PathVariable String accessToken) {
        try {
            if (userService.validateToken(accessToken) != null) {
                String authId = jwtUtils.getAuthIdByAccessToken(accessToken);
                User user = userService.findUserByAuthId(authId);
                return ResponseEntity.status(HttpStatus.OK).body(user.getFcmToken());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* accessToken/fcmToken -> fcmToken 저장 */
    @PostMapping("/save-fcm-token/{accessToken}/{fcmToken}")
    public ResponseEntity<String> saveFcmToken(@PathVariable String accessToken, @PathVariable String fcmToken) {
        try {
            if (userService.validateToken(accessToken) != null) {
                userService.modifyFcmToken(accessToken, fcmToken);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}