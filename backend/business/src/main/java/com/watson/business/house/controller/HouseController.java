package com.watson.business.house.controller;

import com.watson.business.house.dto.search.HouseListResponse;
import com.watson.business.house.dto.search.HouseResponse;
import com.watson.business.house.service.HouseSearchService;
import com.watson.business.house.service.HouseService;
import com.watson.business.house.dto.houseregist.HouseRegistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseSearchService houseSearchService;

    // 매물 등록
    @PostMapping
    public ResponseEntity<String> registHouse(@RequestBody HouseRegistRequest houseRegistRequest) {

        // access_token으로 realtor_id 가져오는 로직 필요
        String realtorId = "realtor_id";

        // S3에 사진 저장하는 로직 필요

        houseService.registHouse(houseRegistRequest, realtorId);

        return ResponseEntity.ok("매물등록 완료");
    }

    @GetMapping
    public List<HouseListResponse> searchAllHouse() {
        return houseSearchService.searchAllHouse();
    }

    @GetMapping("/{id}")
    public HouseResponse searchHouseById(@PathVariable Long id) {
        return houseSearchService.searchHouseById(id);
    }

}
