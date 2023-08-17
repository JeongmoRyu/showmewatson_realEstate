package com.watson.business.connect.service;

import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.WeeklyRankResponse;
import com.watson.business.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectViewCountService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final HouseService houseService;

    public ResponseEntity<List<WeeklyRankResponse>> weeklyRankByDongleeName(String dongleeName) {
        String targetServerUrl = "http://view-count:8084/weekly-rank/" + dongleeName;
        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl).toUriString();
        ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });

        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        List<String> weeklyRankList = response.getBody();
        List<WeeklyRankResponse> result = new ArrayList<>();
        for (String id : weeklyRankList) {
            Long houseId = Long.parseLong(id);
            HouseDetailResponse houseDetailResponse = houseService.findHouseByHouseId(houseId);
            WeeklyRankResponse house = WeeklyRankResponse.builder()
                    .houseId(houseDetailResponse.getHouseId())
                    .deposit(houseDetailResponse.getDeposit())
                    .fileNames(houseDetailResponse.getFileNames())
                    .title(houseDetailResponse.getTitle())
                    .monthlyRent(houseDetailResponse.getMonthlyRent())
                    .squareMeter(houseDetailResponse.getSquareMeter())
                    .supplyAreaMeter(houseDetailResponse.getSupplyAreaMeter())
                    .address(houseDetailResponse.getAddress()).build();
            result.add(house);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
