package com.watson.business.house.dto.houseresponse;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeklyRankResponse {
    private Long houseId;
    private String title;
    private List<String> fileNames;
    private Long deposit;
    private Long monthlyRent;
    private double squareMeter;
    private double supplyAreaMeter;
    private String address;
}
