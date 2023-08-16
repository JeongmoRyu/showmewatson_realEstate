package com.watson.business.realtor.controller;

import com.watson.business.connect.service.ConnectAuthService;
import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/realtor")
@RequiredArgsConstructor
@Slf4j
public class RealtorController {

    private final ConnectAuthService connectAuthService;
    private final HouseService houseService;

    @GetMapping("/{realtorId}")
    public ResponseEntity<String> getRealtorDetail(@PathVariable String realtorId) {
        return connectAuthService.getRealtorDetail(realtorId);
    }

    @GetMapping("/{realtorId}/house")
    public ResponseEntity<List<HouseListResponse>> getHouseDetailByRealtor(@PathVariable String realtorId, @RequestHeader String authorization) {
        log.info("getRealtorDetail: {}", realtorId);

        HttpStatus loginStatus = HttpStatus.UNAUTHORIZED;
        if(loginStatus == HttpStatus.UNAUTHORIZED) { // 로그인 안한 사용자
            return ResponseEntity.status(HttpStatus.OK).body(houseService.findAllHousesByRealtorId(realtorId));
        }
        else if (loginStatus == HttpStatus.OK){     //  HttpStatus.OK : 로그인 완료된 사용자
            String userId = "admin";
            return ResponseEntity.status(HttpStatus.OK).body(houseService.findAllHousesWithIsWishByRealtorId(realtorId, userId));
        } else {
            throw new HouseException(HouseErrorCode.NOT_FOUND_EXCEPTION);
        }
    }
}
