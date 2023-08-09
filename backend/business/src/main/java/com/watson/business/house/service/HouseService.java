package com.watson.business.house.service;

import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseService {
    List<HouseListResponse> findAllHouses();
    HouseDetailResponse findHouseByHouseId(Long houseId);
    Long addHouse(List<MultipartFile> file, HouseRegistRequest houseRegistRequest, String realtorId);
    Long modifyHouse(Long houseId, List<MultipartFile> file, HouseUpdateRequest houseUpdateRequest, String realtorId);
    List<HouseListResponse> findAllHousesWithIsWish(String userId);
//    사용자의 관심매물 리스트
    List<HouseListResponse> findWishedHousesByUserId(String userId);
}
