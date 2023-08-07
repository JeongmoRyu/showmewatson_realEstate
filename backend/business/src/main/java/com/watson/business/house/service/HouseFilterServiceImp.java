package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.houserequest.HouseFilterParamRequest;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.house.filter.HouseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.runtime.internal.Conversions.intValue;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseFilterServiceImp implements HouseFilterService {
    private final HouseRepository houseRepository;
    @Override
    public List<HouseListResponse> findFilterHouses(HouseFilterParamRequest filterParam) {
        Specification<House> spec = (root, query, criteriaBuilder) -> null;

        if (filterParam.getHouseCode() > 0) {
            spec = spec.and(HouseSpecification.equalHouseCode(filterParam.getHouseCode()));
        }

        // 다른 필터 조건 메서드로 분리하여 호출
        spec = addSquareMeterConditions(spec, filterParam);
        spec = addContractCodeConditions(spec, filterParam);

        // 최종 필터 조건 적용
        List<HouseListResponse> houseListResponses = new ArrayList<>();
        List<House> houseEntities = houseRepository.findAll(spec);
        for (House house : houseEntities) {
            HouseListResponse response = listEntityToDto(house);
            houseListResponses.add(response);
        }

        return houseListResponses;
    }
    // 면적 조건 추가 메서드
    private Specification<House> addSquareMeterConditions(Specification<House> spec, HouseFilterParamRequest filterParam) {
        if (filterParam.getMinSquareMeter() != 0) {
            spec = spec.and(HouseSpecification.graterThanSquareMeter(filterParam.getMinSquareMeter()));
        }
        if (filterParam.getMaxSquareMeter() != 0) {
            spec = spec.and(HouseSpecification.lessThanSquareMeter(filterParam.getMaxSquareMeter()));
        }
        return spec;
    }

    // 계약 유형 조건 추가 메서드
    private Specification<House> addContractCodeConditions(Specification<House> spec, HouseFilterParamRequest filterParam) {
        if (filterParam.getContractCode() > 0) {
            spec = spec.and(HouseSpecification.equalContractCode(filterParam.getContractCode()));
            switch (filterParam.getContractCode()) {
                case 1: case 2:    // 1. 월세(보증금, 관리비, 월세)   2. 전세(보증금, 관리비)
                    spec = addDepositAndMaintenanceConditions(spec, filterParam);
                    break;
                case 3:     // 3. 매매(매매가)
                    spec = addSalePriceConditions(spec, filterParam);
                    break;
                default:
                    throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
            }
        }
        return spec;
    }

    // 월세와 전세 관련 조건 추가 메서드
    private Specification<House> addDepositAndMaintenanceConditions(Specification<House> spec, HouseFilterParamRequest filterParam) {
        if (filterParam.getMinDeposit() != null) {
            spec = spec.and(HouseSpecification.graterThanOrEqualDeposit(intValue(filterParam.getMinDeposit())));
        }
        if (filterParam.getMaxDeposit() != null) {
            spec = spec.and(HouseSpecification.lessThanOrEqualDeposit(intValue(filterParam.getMaxDeposit())));
        }
        if (filterParam.getMinMaintenance() != null) {
            spec = spec.and(HouseSpecification.graterThanOrEqualMaintenance(intValue(filterParam.getMinMaintenance())));
        }
        if (filterParam.getMaxMaintenance() != null) {
            spec = spec.and(HouseSpecification.lessThanOrEqualMaintenance(intValue(filterParam.getMaxMaintenance())));
        }
        return spec;
    }

    // 매매 관련 조건 추가 메서드
    private Specification<House> addSalePriceConditions(Specification<House> spec, HouseFilterParamRequest filterParam) {
        if (filterParam.getMinSalePrice() != null) {
            spec = spec.and(HouseSpecification.graterThanSalePrice(intValue(filterParam.getMinSalePrice())));
        }
        if (filterParam.getMaxSalePrice() != null) {
            spec = spec.and(HouseSpecification.lessThanSalePrice(intValue(filterParam.getMaxSalePrice())));
        }
        return spec;
    }

    private HouseListResponse listEntityToDto(House house) {
        return HouseListResponse.builder()
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
    }

}
