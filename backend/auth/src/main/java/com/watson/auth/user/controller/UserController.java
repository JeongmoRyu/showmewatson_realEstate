package com.watson.auth.user.controller;

import com.watson.auth.user.dto.UserResponse;
import com.watson.auth.user.dto.UserUpdateRequest;
import com.watson.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth/user")
public class UserController {

    private final UserService userService;

    /* 정보 수정 (닉네임) */
    @PutMapping
    public ResponseEntity<String> userModify(@RequestPart UserUpdateRequest userUpdateRequest) {
        try {
            String accessToken = userUpdateRequest.getAccessToken();
            log.info("accessToken : " + accessToken);
            if (userService.validateToken(accessToken)) {
                log.info("유효한 토큰입니다.");
                userService.modifyNickname(accessToken, userUpdateRequest.getNickname());
                log.info("닉네임을 수정하였습니다.");

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                log.info("유효하지 않은 토큰입니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 유효하지 않은 토큰
            }
        } catch (Exception e) {
            log.info("INTERNAL_SERVER_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* 탈퇴 */


    /* 회원 정보 조회 */
    @GetMapping
    public ResponseEntity<UserResponse> userDetails(@RequestHeader("Authorization") String accessToken) {
        log.info("try 직전");
        try {
            log.info("nickname 초기설정");
            String nickname = "";
            log.info("accessToken : " + accessToken);
            if (userService.validateToken(accessToken)) {
                log.info("findNickname 전");
                nickname = userService.findNicknameByAccessToken(accessToken);
                log.info("nickname : " + nickname);
            } else {
                log.info("UNAUTHORIZED");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 유효하지 않은 토큰
            }
            UserResponse userResponse = UserResponse.builder()
                    .nickname(nickname)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("INTERNAL_SERVER_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}