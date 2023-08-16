package com.watson.business.realtor.controller;

import com.watson.business.connect.service.ConnectAuthService;
import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.dto.item.STATUS;
import com.watson.business.house.service.HouseService;
import com.watson.business.realtor.dto.RealtorInfoResponse;
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
    public ResponseEntity<RealtorInfoResponse> getRealtorDetail(@PathVariable String realtorId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(connectAuthService.getRealtorDetail(realtorId));
        } catch (Exception e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_REALTOR);
        }
    }

    @GetMapping("/{realtorId}/house")
    public ResponseEntity<List<HouseListResponse>> getHouseDetailByRealtor(@RequestHeader("Authorization") String accessToken, @PathVariable String realtorId) {
        log.info("getRealtorDetail: {}", realtorId);
        String userId = connectAuthService.getUserId(accessToken);
        try {
            if (userId == null) { // 로그인 안한 사용자
                return ResponseEntity.status(HttpStatus.OK).body(houseService.findAllHousesByRealtorId(realtorId));
            } else {     //  HttpStatus.OK : 로그인 완료된 사용자
                return ResponseEntity.status(HttpStatus.OK).body(houseService.findAllHousesWithIsWishByRealtorId(realtorId, userId));
            }
        } catch (Exception e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_LIST);
        }
    }
}
