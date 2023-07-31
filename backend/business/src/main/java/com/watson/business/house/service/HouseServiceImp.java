package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.houseContractInfoDetail.MonthlyInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.SaleInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.YearlyInfo;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.houseRequest.ContractRequest;
import com.watson.business.house.dto.houseRequest.HouseOptionRequest;
import com.watson.business.house.dto.houseRequest.HouseRequest;
import com.watson.business.house.dto.houseResponse.HouseListResponse;
import com.watson.business.house.dto.houseResponse.HouseDetailResponse;
import com.watson.business.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.watson.business.house.dto.item.STATUS.TRADING;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImp implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseImageServiceImp houseImageService;
    private final RegionService regionService;

    public List<HouseListResponse> findAllHouses() {
        List<House> houseEntityList = houseRepository.findAll();
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseEntityList) {
            HouseListResponse houseListResponse = listEntityToDto(h);

            switch (h.getContractCode()) {
                case 1:    // 월세
                    houseListResponse.setDeposit(h.getMonthlyInfo().getDeposit());
                    houseListResponse.setMaintenance(h.getMonthlyInfo().getMaintenance());
                    houseListResponse.setMonthlyRent(h.getMonthlyInfo().getMonthlyRent());
                    break;

                case 2:    // 전세
                    houseListResponse.setDeposit(h.getYearlyInfo().getDeposit());
                    houseListResponse.setMaintenance(h.getYearlyInfo().getMaintenance());
                    break;

                case 3:
                    houseListResponse.setSalePrice(h.getSaleInfo().getSalePrice());
                    break;// 매매
                default:
                    throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
            }

            // TODO: access-key 존재시 isWish 관련 기능 구현  : 매물(h.getId()) + wish테이블

            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }

    public HouseDetailResponse findHouseByHouseId(Long houseId) {
        House house = houseRepository.findHouseById(houseId);
        if (house == null) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

        HouseListResponse houseListResponse = listEntityToDto(house);

        HouseDetailResponse houseDetailResponse = HouseDetailResponse.builder()
                .houseListResponse(houseListResponse)
//                .realtor(house.getRealtorId())
                .maintenanceList(house.getMaintenanceList())
                .contractCode(house.getContractCode())
                .contractCode(house.getContractCode())
                .totalFloor(house.getTotalFloor())
                .buildingUse(house.getBuildingUse())
                .approvalBuildingDate(house.getApprovalBuildingDate())
                .bathroom(house.getBathroom())
                .content(house.getContent())
                .regDate(house.getRegDate())
                .houseOption(house.getHouseOption())
                .build();

//        private RealtorResponse realtor;
//        private EmdResponse emdResponse;
//        private List<HouseFile> houseFiles;
//        RealtorResponse realtorResponse = new RealtorResponse(null, house.getRealtorId());  // realtorName 로직 필요
        switch (house.getContractCode()) {
            case 1:      // 월세
                houseListResponse.setDeposit(house.getMonthlyInfo().getDeposit());
                houseListResponse.setMaintenance(house.getMonthlyInfo().getMaintenance());
                houseDetailResponse.setMaintenanceList(house.getMonthlyInfo().getMaintenanceList());
                houseListResponse.setMonthlyRent(house.getMonthlyInfo().getMonthlyRent());
                break;

            case 2:   // 전세
                houseListResponse.setDeposit(house.getYearlyInfo().getDeposit());
                houseDetailResponse.setMaintenanceList(house.getMonthlyInfo().getMaintenanceList());
                houseListResponse.setMaintenance(house.getYearlyInfo().getMaintenance());
                break;

            case 3:
                houseListResponse.setSalePrice(house.getSaleInfo().getSalePrice());
                break;// 매매
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }



//        // TODO: isWish 로직 필요

        return houseDetailResponse;
    }

    public Long addHouse(List<MultipartFile> file, HouseRequest houseRequest, String realtorId) {

        ContractRequest contractRequest = houseRequest.getContractInfo();
//        HouseOptionRequest houseOption = houseRequest.getHouseOptionRequest();



//        realtorId 받아오기

        House house = House.builder()
                .contractCode(houseRequest.getContractCode())
                .dongCode(houseRequest.getDongCode())
                .houseCode(houseRequest.getHouseCode())
                .squareMeter(houseRequest.getSquareMeter())
                .floor(houseRequest.getFloor())
                .totalFloor(houseRequest.getTotalFloor())
                .address(houseRequest.getAddress())
                .title(houseRequest.getTitle())
                .content(houseRequest.getContent())
                .supplyAreaMeter(houseRequest.getSupplyAreaMeter())
                .buildingUse(houseRequest.getBuildingUse())
                .approvalBuildingDate(houseRequest.getApprovalBuildingDate())
                .bathroom(houseRequest.getBathroom())
                .weeklyViewCount(0)
                .status(TRADING)
                .realtorId(realtorId)
                .houseFiles(new ArrayList<>())
                .build();

        // 이미지 저장
        List<String> houseFileList = houseImageService.addFile(file, "house");
        for (String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }

        /**
         * 1: 월세
         * 2: 전세
         * 3: 매매
         */
        switch (house.getContractCode()) {
            case 1:
                MonthlyInfo monthInfo = MonthlyInfo.builder()
                        .deposit(contractRequest.getDeposit())
                        .monthlyRent(contractRequest.getMonthlyRent())
                        .maintenance(contractRequest.getMaintenance())
                        .maintenanceList(contractRequest.getMaintenanceList())
                        .build();
                house.setMonthlyInfo(monthInfo);
                break;
            case 2:
                YearlyInfo yearlyInfo = YearlyInfo.builder()
                        .deposit(contractRequest.getDeposit())
                        .maintenance(contractRequest.getMaintenance())
                        .maintenanceList(contractRequest.getMaintenanceList())
                        .build();
                house.setYearlyInfo(yearlyInfo);
                break;
            case 3:
                SaleInfo saleInfo = SaleInfo.builder()
                        .salePrice(contractRequest.getSalePrice())
                        .build();
                house.setSaleInfo(saleInfo);
                break;
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

        return houseRepository.save(house).getId();
    }

    private HouseListResponse listEntityToDto(House house) {
        HouseListResponse houseListResponse = HouseListResponse.builder()
                .houseId(house.getId())
                .houseCode(house.getHouseCode())
                .squareMeter(house.getSquareMeter())
                .suppleAreaMeter(house.getSupplyAreaMeter())
                .floor(house.getFloor())
                .address(house.getAddress())
                .title(house.getTitle())
                .status(house.getStatus())
//                .fileName(house.getHouseFiles().get(0).getFileName())
                .build();
        return houseListResponse;
    }
}
