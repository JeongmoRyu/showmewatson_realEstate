package com.watson.business.house.service;

import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.houseinfo.MonthlyInfos;
import com.watson.business.house.domain.entity.houseinfo.SaleInfos;
import com.watson.business.house.domain.entity.houseinfo.YearlyInfos;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.search.HouseListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseSearchService {
    @Autowired
    private HouseRepository houseRepository;

    public List<HouseListResponse> searchAllHouse() {
        List<House> houseList = houseRepository.findAll();
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for(House h : houseList){
            HouseListResponse houseListResponse = new HouseListResponse(
                    h.getId(), h.getHouseCode(), h.getSquareMeter(), h.getSupplyAreaMeter(), h.getFloor(), h.getAddress(), h.getContent(), h.getStatus());


            switch (h.getContractCode()) {
                case 1 -> {     // 월세
                    houseListResponse.setDeposit(h.getMonthlyInfos().getDeposit());
                    houseListResponse.setMaintenance(h.getMonthlyInfos().getMaintenance());
                }
                case 2 -> {     // 전세
                    houseListResponse.setDeposit(h.getYearlyInfos().getDeposit());
                    houseListResponse.setMaintenance(h.getYearlyInfos().getMaintenance());
                }
                case 3 -> {     // 매매
                    houseListResponse.setSalePrice(h.getSaleInfos().getSalePrice());
                }
                default -> {
                    // 오류
                }
            }
            // 사진 정보 관련 기능 구현

            // access-key 존재시 isWish 관련 기능 구현  : 매물(h.getId()) + wish테이블

            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }
}
