package com.watson.business.house.service;

import com.watson.business.house.dto.houserequest.HouseFilterParamRequest;
import com.watson.business.house.dto.houseresponse.HouseListResponse;

import java.util.List;

public interface HouseFilterService {
    List<HouseListResponse> findFilterHouses(HouseFilterParamRequest filterParam);
}
