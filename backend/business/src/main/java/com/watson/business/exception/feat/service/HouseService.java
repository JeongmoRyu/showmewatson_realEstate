package com.watson.business.exception.feat.service;

import com.watson.business.exception.feat.domain.entity.House.House;
import com.watson.business.exception.feat.domain.entity.House.HouseOption;
import com.watson.business.exception.feat.domain.repository.House.HouseRepository;
import com.watson.business.exception.feat.domain.entity.House.houseinfo.MonthlyInfos;
import com.watson.business.exception.feat.domain.entity.House.houseinfo.SaleInfos;
import com.watson.business.exception.feat.domain.entity.House.houseinfo.YearlyInfos;
import com.watson.business.exception.feat.dto.houseregist.HouseRegistRequest;
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
