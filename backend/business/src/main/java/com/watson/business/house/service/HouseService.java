package com.watson.business.house.service;

import com.watson.business.house.dto.houseRequest.HouseRequest;
import com.watson.business.house.dto.houseResponse.HouseListResponse;
import com.watson.business.house.dto.houseResponse.HouseDetailResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseService {
    List<HouseListResponse> findAllHouses();
    HouseDetailResponse findHouseByHouseId(Long houseId);
    Long addHouse(List<MultipartFile> file, HouseRequest houseRequest, String realtorId);
}
