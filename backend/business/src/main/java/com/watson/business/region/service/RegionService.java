package com.watson.business.region.service;

import com.watson.business.region.dto.EmdNameResponse;
import com.watson.business.region.dto.EmdResponse;

import java.util.List;

public interface RegionService {
    List<String> getGunguNamesBySido(String sido);
    List<EmdResponse> getCourtCodesByGungu(String gunguName);
    EmdNameResponse getEmdNameByEmdCode(String courtCode);
}
