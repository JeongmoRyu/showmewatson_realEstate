package com.watson.business.house.service;

import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.domain.entity.houseinfo.MonthlyInfos;
import com.watson.business.house.domain.entity.houseinfo.SaleInfos;
import com.watson.business.house.domain.entity.houseinfo.YearlyInfos;
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
            case 1 -> {
                MonthlyInfos monthInfos = new MonthlyInfos(houseRegistRequest.getContractInfo());
                house.setMonthlyInfos(monthInfos);
            }
            case 2 -> {
                YearlyInfos yearlyInfos = new YearlyInfos(houseRegistRequest.getContractInfo());
                house.setYearlyInfos(yearlyInfos);
            }
            case 3 -> {
                SaleInfos saleInfos = new SaleInfos(houseRegistRequest.getContractInfo());
                house.setSaleInfos(saleInfos);
            }
            default -> {
                // 오류
            }
        }

        houseRepository.save(house);

        return house.getId();
    }
}
