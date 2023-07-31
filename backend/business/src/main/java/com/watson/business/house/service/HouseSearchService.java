package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
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

@Service
public class HouseSearchService {
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private RegionService regionService;

    public List<HouseListResponse> searchAllHouse() {
        List<House> houseList = houseRepository.findHouses();
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseList) {
            HouseListResponse houseListResponse = new HouseListResponse(
                    h.getId(), h.getHouseCode(), h.getSquareMeter(), h.getSupplyAreaMeter(), h.getFloor(), h.getAddress(), h.getContent(), h.getStatus(), h.getHouseFiles().get(0).getFileName());


            switch (h.getContractCode()) {
                case 1:    // 월세
                    houseListResponse.setDeposit(h.getMonthlyInfos().getDeposit());
                    houseListResponse.setMaintenance(h.getMonthlyInfos().getMaintenance());
                    houseListResponse.setMonthlyRent(h.getMonthlyInfos().getMonthlyRent());
                    break;

                case 2:    // 전세
                    houseListResponse.setDeposit(h.getYearlyInfos().getDeposit());
                    houseListResponse.setMaintenance(h.getYearlyInfos().getMaintenance());
                    break;

                case 3:
                    houseListResponse.setSalePrice(h.getSaleInfos().getSalePrice());
                    break;// 매매
                default:
                    throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
            }

            // TODO: access-key 존재시 isWish 관련 기능 구현  : 매물(h.getId()) + wish테이블

            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }

    public HouseResponse searchHouseById(Long houseId) {
        House house= houseRepository.findHouseById(houseId);
        if (house == null) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

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
            case 1:      // 월세
                houseResponse.setDeposit(house.getMonthlyInfos().getDeposit());
                houseResponse.setMaintenance(house.getMonthlyInfos().getMaintenance());
                houseResponse.setMaintenanceList(house.getMonthlyInfos().getMaintenanceList());
                houseResponse.setMonthlyRent(house.getMonthlyInfos().getMonthlyRent());
                break;

            case 2:   // 전세
                houseResponse.setDeposit(house.getYearlyInfos().getDeposit());
                houseResponse.setMaintenanceList(house.getMonthlyInfos().getMaintenanceList());
                houseResponse.setMaintenance(house.getYearlyInfos().getMaintenance());
                break;

            case 3:
                houseResponse.setSalePrice(house.getSaleInfos().getSalePrice());
                break;// 매매
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

        // TODO: isWish 로직 필요

        return houseResponse;
    }
}
