package com.watson.business.house.service;

import com.watson.business.house.dto.houserequest.HouseFilterParamRequest;
import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseService {
    List<HouseListResponse> findAllHouses();
    HouseDetailResponse findHouseByHouseId(Long houseId);
    Long addHouse(List<MultipartFile> file, HouseRegistRequest HouseRegistRequest, String realtorId);
//    매물 필터링 목록
    Long modifyHouse(Long houseId, HouseUpdateRequest houseUpdateRequest, String realtorId);
}
