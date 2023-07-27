package com.watson.business.house.service;

import com.watson.business.house.domain.entity.house.House;
import com.watson.business.house.domain.entity.house.HouseOption;
import com.watson.business.house.domain.repository.house.HouseRepository;
import com.watson.business.house.domain.entity.house.houseinfo.MonthlyInfos;
import com.watson.business.house.domain.entity.house.houseinfo.SaleInfos;
import com.watson.business.house.domain.entity.house.houseinfo.YearlyInfos;
import com.watson.business.house.dto.houseregist.HouseRegistRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    public Long registHouse(HouseRegistRequest houseRegistRequest, String realtorId) {
        HouseOption houseOption = new HouseOption(houseRegistRequest.getHouseOption());

        House house = new House(realtorId, houseRegistRequest, houseOption);
        /**
         * 1: 월세
         * 2: 전세
         * 3: 매매
         */
        switch (house.getContractCode()) {
            case 1:
                MonthlyInfos monthInfos = new MonthlyInfos(houseRegistRequest.getContractInfo());
                house.setMonthlyInfos(monthInfos);
                break;
            case 2:
                YearlyInfos yearlyInfos = new YearlyInfos(houseRegistRequest.getContractInfo());
                house.setYearlyInfos(yearlyInfos);
                break;
            case 3:
                SaleInfos saleInfos = new SaleInfos(houseRegistRequest.getContractInfo());
                house.setSaleInfos(saleInfos);
                break;
            default:
                // 오류
                break;
        }

        houseRepository.save(house);

        return house.getHouseId();
    }
}
