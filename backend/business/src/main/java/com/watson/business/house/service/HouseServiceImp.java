package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.house.domain.entity.housecontractinfodetail.MonthlyInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.SaleInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.YearlyInfo;
import com.watson.business.house.domain.repository.HouseFileRepository;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.houserequest.ContractRequest;
import com.watson.business.house.dto.houserequest.HouseOptionRequest;
import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.dto.houseresponse.HouseOptionResponse;
import com.watson.business.region.dto.EmdNameResponse;
import com.watson.business.region.service.RegionService;
import com.watson.business.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.watson.business.house.dto.item.STATUS.TRADING;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImp implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseFileRepository houseFileRepository;
    private final HouseImageServiceImp houseImageService;
    private final RegionService regionService;
    private final WishService wishService;

    public List<HouseListResponse> findAllHouses() {
        List<House> houseEntityList = houseRepository.findAllHousesWithFiles();
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseEntityList) {
            EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(h.getCourtCode());
            HouseListResponse houseListResponse = houseListEntityToDto(h, emdNameResponse);

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

            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }

    public HouseDetailResponse findHouseByHouseId(Long houseId) {
        House house = houseRepository.findHouseById(houseId);
        EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(house.getCourtCode());
        log.info("{}", house.getHouseOption());
        if (house == null) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }


        HouseDetailResponse houseDetailResponse = HouseDetailResponse.builder()
//                .realtor(house.getRealtorId())
                .maintenanceList(house.getMaintenanceList())
                .contractCode(house.getContractCode())
                .totalFloor(house.getTotalFloor())
                .buildingUse(house.getBuildingUse())
                .approvalBuildingDate(house.getApprovalBuildingDate())
                .bathroom(house.getBathroom())
                .room(house.getRoom())
                .content(house.getContent())
                .regDate(house.getRegDate())
                .build();

        HouseListResponse houseListResponse = houseListEntityToDto(house, emdNameResponse);
        houseDetailResponse.setHouseListResponse(houseListResponse);
        houseDetailResponse.setHouseOption(houseOptionEntityToDto(house.getHouseOption()));

        switch (house.getContractCode()) {
            case 1:      // 월세
                houseDetailResponse.setDeposit(house.getMonthlyInfo().getDeposit());
                houseDetailResponse.setMaintenance(house.getMonthlyInfo().getMaintenance());
                houseDetailResponse.setMaintenanceList(house.getMonthlyInfo().getMaintenanceList());
                houseDetailResponse.setMonthlyRent(house.getMonthlyInfo().getMonthlyRent());
                break;

            case 2:   // 전세
                houseDetailResponse.setDeposit(house.getYearlyInfo().getDeposit());
                houseDetailResponse.setMaintenanceList(house.getMonthlyInfo().getMaintenanceList());
                houseDetailResponse.setMaintenance(house.getYearlyInfo().getMaintenance());
                break;

            case 3:
                houseDetailResponse.setSalePrice(house.getSaleInfo().getSalePrice());
                break;// 매매
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

//        // TODO: isWish 로직 필요

        return houseDetailResponse;
    }

    public Long addHouse(List<MultipartFile> file, HouseRegistRequest request, String realtorId) {

        ContractRequest contractRequest = request.getContractInfo();
        HouseOptionRequest houseOptionRequest = request.getHouseOption();
        log.debug("{}", request);
        log.debug("{}", houseOptionRequest);
//        realtorId 받아오기

        House house = House.builder()
                .contractCode(request.getContractCode())
                .courtCode(request.getCourtCode())
                .houseCode(request.getHouseCode())
                .squareMeter(request.getSquareMeter())
                .floor(request.getFloor())
                .totalFloor(request.getTotalFloor())
                .address(request.getAddress())
                .title(request.getTitle())
                .content(request.getContent())
                .supplyAreaMeter(request.getSupplyAreaMeter())
                .buildingUse(request.getBuildingUse())
                .approvalBuildingDate(request.getApprovalBuildingDate())
                .bathroom(request.getBathroom())
                .room(request.getRoom())
                .weeklyViewCount(0)
                .status(TRADING)
                .realtorId(realtorId)
                .houseFiles(new ArrayList<>())
                .houseOption(houseOptionDtoToEntity(houseOptionRequest))
                .build();

        // 이미지 저장
        List<String> houseFileList = houseImageService.addFile(file, "house");
        for(String fn : houseFileList) {
            houseFileRepository.save(HouseFile.builder()
                    .fileName(fn)
                    .house(house)
                    .isDeleted(false)
                    .build());
        }
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
    public Long modifyHouse(Long houseId, List<MultipartFile> file, HouseUpdateRequest houseUpdateRequest, String realtorId) {
        House house = houseRepository.findHouseById(houseId);
        house.editHousePost(houseUpdateRequest.getTitle(), houseUpdateRequest.getContent());
//        가격 수정
        switch (house.getContractCode()) {
            case 1:
                MonthlyInfo monthInfo = MonthlyInfo.builder()
                        .deposit(houseUpdateRequest.getDeposit())
                        .monthlyRent(houseUpdateRequest.getMonthlyRent())
                        .maintenance(houseUpdateRequest.getMaintenance())
                        .build();
                house.editHouseMonthlyInfo(monthInfo);
                break;
            case 2:
                YearlyInfo yearlyInfo = YearlyInfo.builder()
                        .deposit(houseUpdateRequest.getDeposit())
                        .maintenance(houseUpdateRequest.getMaintenance())
                        .build();
                house.editHouseYearlyInfo(yearlyInfo);
                break;
            case 3:
                SaleInfo saleInfo = SaleInfo.builder()
                        .salePrice(houseUpdateRequest.getSalePrice())
                        .build();
                house.editHouseSaleInfo(saleInfo);
                break;
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

//        기존 파일 삭제 : houseId를 기준으로 찾아오기
        List<HouseFile> deleteHouseFileList = houseFileRepository.findHouseFileByHouseId(houseId);
        for(HouseFile d : deleteHouseFileList) {
            houseFileRepository.delete(d);
        }
        // 이미지 저장
        List<String> houseFileList = houseImageService.addFile(file, "house");
        for(String fn : houseFileList) {
            houseFileRepository.save(HouseFile.builder()
                    .fileName(fn)
                    .house(house)
                    .isDeleted(false)
                    .build());
        }
        for (String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }
        return houseId;
    }

    @Override
    public List<HouseListResponse> findAllHousesWithIsWish(String userId) {
        List<House> houseEntityList = houseRepository.findAllHousesWithFiles();
        List<Long> isWiehedList = wishService.findWishedHouseIdByUserId(userId);
        log.debug("{}", userId);
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseEntityList) {
            EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(h.getCourtCode());
            HouseListResponse houseListResponse = houseListEntityToDto(h, emdNameResponse);

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

            if(isWiehedList.contains(h.getId())) {
                houseListResponse.setWished(true);
            }
            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }

    @Override
    public List<HouseListResponse> findWishedHousesByUserId(String userId) {
        List<Long> houseIds = wishService.findWishedHouseIdByUserId(userId);
        log.debug("{}", userId);
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for(Long id : houseIds) {
            House house = houseRepository.findHouseById(id);
            EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(house.getCourtCode());
            HouseListResponse houseListResponse = houseListEntityToDto(house, emdNameResponse);
            houseListResponse.setWished(true);
            allHouseList.add(houseListResponse);
        }
        return allHouseList;
    }

    private HouseListResponse houseListEntityToDto(House house, EmdNameResponse emdNameResponse) {
        HouseListResponse response =  HouseListResponse.builder()
                .id(house.getId())
                .houseCode(house.getHouseCode())
                .squareMeter(house.getSquareMeter())
                .supplyAreaMeter(house.getSupplyAreaMeter())
                .floor(house.getFloor())
                .address(house.getAddress())
                .title(house.getTitle())
                .status(house.getStatus())
                .sidoName(emdNameResponse.getSidoName())
                .gunguName(emdNameResponse.getGunguName())
                .dongleeName(emdNameResponse.getDongLeeName())
                .build();
        if(!house.getHouseFiles().isEmpty()) {
            List<String> fileNames = house.getHouseFiles().stream()
                    .map(HouseFile::getFileName)
                    .collect(Collectors.toList());
            response.setFileNames(fileNames);

        }
        return response;
    }

    private HouseOption houseOptionDtoToEntity(HouseOptionRequest houseOptionRequest) {
        return HouseOption.builder()
                .sink(houseOptionRequest.isSink())
                .airConditioner(houseOptionRequest.isAirConditioner())
                .shoeCloset(houseOptionRequest.isShoeCloset())
                .washingMachine(houseOptionRequest.isWashingMachine())
                .refrigerator(houseOptionRequest.isRefrigerator())
                .closet(houseOptionRequest.isCloset())
                .induction(houseOptionRequest.isInduction())
                .desk(houseOptionRequest.isDesk())
                .elevator(houseOptionRequest.isElevator())
                .coldHeating(houseOptionRequest.isColdHeating())
                .parking(houseOptionRequest.isParking())
                .build();
    }
    private HouseOptionResponse houseOptionEntityToDto(HouseOption houseOption) {
        return HouseOptionResponse.builder()
                .sink(houseOption.isSink())
                .airConditioner(houseOption.isAirConditioner())
                .shoeCloset(houseOption.isShoeCloset())
                .washingMachine(houseOption.isWashingMachine())
                .refrigerator(houseOption.isRefrigerator())
                .closet(houseOption.isCloset())
                .induction(houseOption.isInduction())
                .desk(houseOption.isDesk())
                .elevator(houseOption.isElevator())
                .coldHeating(houseOption.isColdHeating())
                .parking(houseOption.isParking())
                .build();
    }
}
