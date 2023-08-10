package com.watson.business.personalfilter.controller;

import com.watson.business.personalfilter.dto.PersonalFilterRequest;
import com.watson.business.personalfilter.dto.PersonalFilterResponse;
import com.watson.business.personalfilter.service.PersonalFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user/filter")
@RequiredArgsConstructor
public class PersonalFilterController {
    private final PersonalFilterService personalFilterService;

    private static String userId = "admin";
    //    나만의 필터 저장, 불러오기, 수정
//    필터 불러오기 : 한 번에 최대 3개 불러와서 저장해놓기
    @GetMapping("")
    public ResponseEntity<List<PersonalFilterResponse>> filterDetailsByUserId() {
//        access token -> userId
        return ResponseEntity.status(HttpStatus.OK).body(personalFilterService.findPersonalFilterByUserId(userId));
    }
    @PostMapping("")
    public ResponseEntity<String> filterAdd(@RequestBody PersonalFilterRequest request) {
        //        access token -> userId
        personalFilterService.addPersonalFilter(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body("나만의 필터 등록 완료");
    }

    @PutMapping("/{filter_id}")
    public ResponseEntity<String> filterModify(@PathVariable("filter_id") Long filterId, @RequestBody PersonalFilterRequest request) {
        personalFilterService.modifyPersonalFilter(userId, filterId, request);
        return ResponseEntity.status(HttpStatus.OK).body("나만의 필터 수정 완료");
    }

}