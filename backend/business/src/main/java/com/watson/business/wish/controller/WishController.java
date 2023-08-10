package com.watson.business.wish.controller;

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

//    임시 아이디
    private final String userId = "admin";

    @GetMapping("")
    public ResponseEntity<List<HouseListResponse>> wishList() {
//      oauth server : access token -> return userId
        return ResponseEntity.status(HttpStatus.OK).body(houseService.findWishedHousesByUserId(userId));
    }
    @PostMapping("")
    public ResponseEntity<String> wishesAdd(@RequestBody WishRequest wishRequest) {
        //  oauth server : access token -> return userId
        wishService.addWish(userId, wishRequest);
        return ResponseEntity.status(HttpStatus.OK).body("관심등록 완료");
    }
    @PutMapping("")
    public ResponseEntity<String> wishesModify(@RequestBody WishRequest wishRequest) {
        //  oauth server : access token -> return userId
        wishService.modifyWish(userId, wishRequest);
        return ResponseEntity.status(HttpStatus.OK).body("관심등록 상태 변경 완료");
    }
}

