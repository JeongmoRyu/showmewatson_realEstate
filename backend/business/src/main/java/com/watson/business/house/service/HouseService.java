package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.domain.entity.houseinfo.MonthlyInfos;
import com.watson.business.house.domain.entity.houseinfo.SaleInfos;
import com.watson.business.house.domain.entity.houseinfo.YearlyInfos;
import com.watson.business.house.dto.houseregist.HouseRegistRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseImageService houseImageService;
    public Long registHouse(List<MultipartFile> file, HouseRegistRequest houseRegistRequest, String realtorId) {
        HouseOption houseOption = new HouseOption(houseRegistRequest.getHouseOption());

        House house = new House(realtorId, houseRegistRequest, houseOption);

        // 이미지 저장
        List<String> houseFileList = houseImageService.uploadFile(file, "house");
        for(String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }

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
            default -> throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

        houseRepository.save(house);

        return house.getId();
    }
}
