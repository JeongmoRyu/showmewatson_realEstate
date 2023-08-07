package com.watson.business.house.controller;

import com.watson.business.house.dto.houserequest.HouseFilterParamRequest;
import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.service.HouseFilterService;
import com.watson.business.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/house")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    private final HouseFilterService houseFilterService;

    @GetMapping("")     // 매물 전체 목록
    public ResponseEntity<List<HouseListResponse>> houseList() {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.findAllHouses());
    }
    @GetMapping("/{id}")  // 매물 상세보기
    public ResponseEntity<HouseDetailResponse> houseDetailsByHouseId(@PathVariable Long id) {
        log.debug("{매물 id ::: }", id);

        return ResponseEntity.status(HttpStatus.OK).body(houseService.findHouseByHouseId(id));
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)    // 매물 게시글 등록
    public ResponseEntity<String> houseAdd(@RequestPart @Valid List<MultipartFile> file, @RequestPart @Valid HouseRegistRequest houseRegistRequest) {
        log.debug("{}", file);
        log.debug("{}", houseRegistRequest);
        // access_token으로 realtor_id 가져오는 로직 필요
        String realtorId = "realtor_id";

        houseService.addHouse(file, houseRegistRequest, realtorId);

        return ResponseEntity.status(HttpStatus.CREATED).body("매물등록 완료");
    }
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> houseModify( @PathVariable Long id, @RequestPart @Valid List<MultipartFile> file, @RequestPart @Valid HouseUpdateRequest houseUpdateRequest) {
        log.debug("{}", houseUpdateRequest);
        log.debug("{매물 id ::: }", id);

        // access_token으로 realtor_id 가져오는 로직 필요
        String realtorId = "realtor_id";

        houseService.modifyHouse(id, file, houseUpdateRequest, realtorId);
        return ResponseEntity.status(HttpStatus.OK).body("매물수정 완료");
    }

//    매물 필터링
    @PostMapping("/filter")
    public ResponseEntity<List<HouseListResponse>> houseFilterList(@RequestBody HouseFilterParamRequest filterParam) {
        log.debug("{}", filterParam);

        return ResponseEntity.status(HttpStatus.OK).body(houseFilterService.findFilterHouses(filterParam));
    }

}
