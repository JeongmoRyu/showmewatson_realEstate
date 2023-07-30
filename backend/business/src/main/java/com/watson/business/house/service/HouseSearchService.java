package com.watson.business.house.service;

import com.watson.business.exception.NotFoundException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.search.HouseListResponse;
import com.watson.business.house.dto.search.HouseResponse;
import com.watson.business.house.dto.search.RealtorResponse;
import com.watson.business.region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseSearchService {
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private RegionService regionService;

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
                case 3 -> houseListResponse.setSalePrice(h.getSaleInfos().getSalePrice());      // 매매
                default -> throw new NotFoundException(404);
            }
            // TODO: 사진 정보 관련 기능 구현

            // TODO: access-key 존재시 isWish 관련 기능 구현  : 매물(h.getId()) + wish테이블

            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }

    public HouseResponse searchHouseById(Long houseId) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if (optionalHouse.isEmpty()) {
            throw new NotFoundException(404);
        }
        House house = optionalHouse.get();

        RealtorResponse realtorResponse = new RealtorResponse(null, house.getRealtorId());  // realtorName 로직 필요
        HouseResponse houseResponse = new HouseResponse(
                realtorResponse,
                house.getId(),
                house.getContractCode(),
                regionService.getDongleeNameByEmdCode(house.getDongCode()),
                house.getHouseCode(),
                house.getSupplyAreaMeter(),
                house.getSquareMeter(),
                house.getFloor(),
                house.getTotalFloor(),
                house.getAddress(),
                house.getBuildingUse(),
                house.getApprovalBuildingDate(),
                house.getBathroom(),
                house.getTitle(),
                house.getContent(),
                house.getStatus(),
                house.getHouseFiles(),
                house.getRegDate(),
                house.getHouseOption()
        );
        switch (house.getContractCode()) {
            case 1 -> {     // 월세
                houseResponse.setDeposit(house.getMonthlyInfos().getDeposit());
                houseResponse.setMaintenance(house.getMonthlyInfos().getMaintenance());
            }
            case 2 -> {     // 전세
                houseResponse.setDeposit(house.getYearlyInfos().getDeposit());
                houseResponse.setMaintenance(house.getYearlyInfos().getMaintenance());
            }
            case 3 -> houseResponse.setSalePrice(house.getSaleInfos().getSalePrice());       // 매매
            default -> throw new NotFoundException(404);
        }

        // TODO: isWish 로직 필요

        return houseResponse;
    }
}
