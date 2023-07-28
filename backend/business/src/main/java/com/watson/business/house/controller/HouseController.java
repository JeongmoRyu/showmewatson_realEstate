package com.watson.business.house.controller;

import com.watson.business.house.service.HouseService;
import com.watson.business.house.dto.houseregist.HouseRegistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    // 매물 등록
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody HouseRegistRequest houseRegistRequest) {

        // access_token으로 realtor_id 가져오는 로직 필요
        String realtorId = "realtor_id";

        // S3에 사진 저장하는 로직 필요

        houseService.registHouse(houseRegistRequest, realtorId);

        return ResponseEntity.ok("매물등록 완료");
    }
}
