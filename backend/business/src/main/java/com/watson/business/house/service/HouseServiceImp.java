package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.housecontractinfodetail.MonthlyInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.SaleInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.YearlyInfo;
import com.watson.business.house.domain.repository.HouseFileRepository;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.houserequest.ContractRequest;
import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.region.dto.EmdNameResponse;
import com.watson.business.region.service.RegionService;
import com.watson.business.wish.service.WishesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    private final WishesService wishesService;

    public List<HouseListResponse> findAllHouses() {
        List<House> houseEntityList = houseRepository.findAllHousesWithFiles();
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseEntityList) {
            EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(h.getCourtCode());
            HouseListResponse houseListResponse = listEntityToDto(h, emdNameResponse);

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
        log.info("{}", house);
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
                .houseOption(house.getHouseOption())
                .build();

        HouseListResponse houseListResponse = listEntityToDto(house, emdNameResponse);
        houseDetailResponse.setHouseListResponse(houseListResponse);

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

    public Long addHouse(List<MultipartFile> file, HouseRegistRequest request, String realtorId) {

        ContractRequest contractRequest = request.getContractInfo();

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
                .build();

        // 이미지 저장
        List<String> houseFileList = houseImageService.addFile(file, "house");
        houseFileRepository.save(HouseFile.builder()
                .fileName(houseFileList.get(0))
                .house(house)
                .isDeleted(false)
                .build());
        for (String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }
        log.debug("{}", houseFileList);
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
        house.editHouse(houseUpdateRequest.getTitle(), houseUpdateRequest.getContent());

//        기존 파일 삭제 : houseId를 기준으로 찾아오기
        List<HouseFile> deleteHouseFileList = houseFileRepository.findHouseFileByHouseId(houseId);
        for(HouseFile d : deleteHouseFileList) {
            houseFileRepository.delete(d);
        }
//        새 파일 추가
        List<String> houseFileList = houseImageService.addFile(file, "house");
        houseFileRepository.save(HouseFile.builder()
                .fileName(houseFileList.get(0))
                .house(house)
                .isDeleted(false)
                .build());
        for (String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }

        return houseId;
    }

    @Override
    public List<HouseListResponse> findAllHousesWithIsWish(String userId) {
        List<House> houseEntityList = houseRepository.findAllHousesWithFiles();
        List<Long> isWiehedList = wishesService.findWishesByUserid(userId);
        log.debug("{}", userId);
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseEntityList) {
            EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(h.getCourtCode());
            HouseListResponse houseListResponse = listEntityToDto(h, emdNameResponse);

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

    private HouseListResponse listEntityToDto(House house, EmdNameResponse emdNameResponse) {
        HouseListResponse response =  HouseListResponse.builder()
                .houseId(house.getId())
                .houseCode(house.getHouseCode())
                .squareMeter(house.getSquareMeter())
                .suppleAreaMeter(house.getSupplyAreaMeter())
                .floor(house.getFloor())
                .address(house.getAddress())
                .title(house.getTitle())
                .status(house.getStatus())
                .sidoName(emdNameResponse.getSidoName())
                .gunguName(emdNameResponse.getGunguName())
                .dongleeName(emdNameResponse.getDongLeeName())
                .build();
        if(!house.getHouseFiles().isEmpty()) {
            response.setFileName(house.getHouseFiles().get(0).getFileName());
        }
        return response;
    }
}
