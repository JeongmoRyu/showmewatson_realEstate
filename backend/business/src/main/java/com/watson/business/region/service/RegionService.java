package com.watson.business.region.service;

import com.watson.business.region.domain.repository.EmdRepository;
import com.watson.business.region.dto.EmdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private EmdRepository emdRepository;

    // 군구 불러오기
    public List<String> getGunguNamesBySido(String sido) {
        return emdRepository.findGunguNamesBySido(sido);
    }

    // 법정동코드 불러오기
    public List<EmdResponse> getCourtCodesByGungu(String gunguName) {
        List<Object[]> courtCodesByGungu = emdRepository.findCourtCodesByGungu(gunguName);
        List<EmdResponse> emdResponseList = new ArrayList<>();

        for(Object[] court : courtCodesByGungu) {
            String courtCode = (String) court[0];
            String dongleeName = (String) court[1];
            EmdResponse emdResponse = new EmdResponse(courtCode, dongleeName);
            emdResponseList.add(emdResponse);
        }

        return emdResponseList;
    }
}
