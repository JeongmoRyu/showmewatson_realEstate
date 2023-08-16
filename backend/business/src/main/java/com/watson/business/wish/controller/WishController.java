package com.watson.business.wish.controller;

import com.watson.business.connect.service.ConnectAuthService;
import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.service.HouseService;
import com.watson.business.wish.dto.WishRequest;
import com.watson.business.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/wish")
@RequiredArgsConstructor
public class WishController {
//    등록, 삭제, 조회(전체)
    private final WishService wishService;
    private final HouseService houseService;
    private final ConnectAuthService connectAuthService;
    @GetMapping("")
    public ResponseEntity<List<HouseListResponse>> wishList(@RequestHeader("Authorization") String accessToken) {
        String userId = connectAuthService.getUserId(accessToken);
        if (userId != null) {
            return ResponseEntity.status(HttpStatus.OK).body(houseService.findWishedHousesByUserId(userId));
        } else {
            throw new HouseException(HouseErrorCode.NOT_FOUND_USER);
        }
    }
    @PostMapping("")
    public ResponseEntity<String> wishesAdd(@RequestHeader("Authorization") String accessToken, @RequestBody WishRequest wishRequest) {
        String userId = connectAuthService.getUserId(accessToken);
        if (userId != null) {
            wishService.addWish(userId, wishRequest);
            return ResponseEntity.status(HttpStatus.OK).body("관심등록 완료");
        } else {
            throw new HouseException(HouseErrorCode.NOT_FOUND_USER);
        }
    }
    @PutMapping("")
    public ResponseEntity<String> wishesModify(@RequestHeader("Authorization") String accessToken, @RequestBody WishRequest wishRequest) {
        String userId = connectAuthService.getUserId(accessToken);
        if (userId != null) {
            wishService.modifyWish(userId, wishRequest);
            return ResponseEntity.status(HttpStatus.OK).body("관심등록 상태 변경 완료");
        } else {
            throw new HouseException(HouseErrorCode.NOT_FOUND_USER);
        }
    }
}

