package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.houseContractInfoDetail.MonthlyInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.SaleInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.YearlyInfo;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.houserequest.ContractRequest;
import com.watson.business.house.dto.houserequest.HouseFilterParamRequest;
import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.filter.HouseSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.watson.business.house.dto.item.STATUS.TRADING;
import static org.aspectj.runtime.internal.Conversions.intValue;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HouseServiceImp implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseImageServiceImp houseImageService;

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
                .room(house.getRoom())
                .content(house.getContent())
                .regDate(house.getRegDate())
                .houseOption(house.getHouseOption())
                .build();

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

    public Long addHouse(List<MultipartFile> file, HouseRegistRequest houseRegistRequest, String realtorId) {

        ContractRequest contractRequest = houseRegistRequest.getContractInfo();

//        realtorId 받아오기

        House house = House.builder()
                .contractCode(houseRegistRequest.getContractCode())
                .dongCode(houseRegistRequest.getDongCode())
                .houseCode(houseRegistRequest.getHouseCode())
                .squareMeter(houseRegistRequest.getSquareMeter())
                .floor(houseRegistRequest.getFloor())
                .totalFloor(houseRegistRequest.getTotalFloor())
                .address(houseRegistRequest.getAddress())
                .title(houseRegistRequest.getTitle())
                .content(houseRegistRequest.getContent())
                .supplyAreaMeter(houseRegistRequest.getSupplyAreaMeter())
                .buildingUse(houseRegistRequest.getBuildingUse())
                .approvalBuildingDate(houseRegistRequest.getApprovalBuildingDate())
                .bathroom(houseRegistRequest.getBathroom())
                .room(houseRegistRequest.getRoom())
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

    @Override
    public Long modifyHouse(Long houseId, HouseUpdateRequest houseUpdateRequest, String realtorId) {
        House house = houseRepository.findHouseById(houseId);
        house.editHouse(houseUpdateRequest.getTitle(), houseUpdateRequest.getContent());
        return houseId;
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
                .maintenance(0)
                .build();
        return houseListResponse;
    }
}
