package com.watson.business.house.controller;

import com.watson.business.house.dto.houseregist.HouseRegistRequest;
import com.watson.business.house.service.HouseSearchService;
import com.watson.business.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseSearchService houseSearchService;

    // 매물 등록
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registHouse(@RequestPart List<MultipartFile> file, @RequestPart HouseRegistRequest houseRegistRequest) {

        // access_token으로 realtor_id 가져오는 로직 필요
        String realtorId = "realtor_id";

        houseService.registHouse(file, houseRegistRequest, realtorId);

        return ResponseEntity.ok("매물등록 완료");
    }
}
