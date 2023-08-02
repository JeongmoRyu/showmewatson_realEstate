package com.watson.business.house.dto.houseRequest;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequest {

    private int contractCode;
    private String dongCode;
    private int houseCode;
    private double squareMeter;
    private int floor;
    private int totalFloor;
    private String address;
    private String title;
    private String content;
    private double supplyAreaMeter;
    private String buildingUse;
    private String approvalBuildingDate;
    private int bathroom;
    private int room;

    private ContractRequest contractInfo;

    private HouseOptionRequest houseOptionRequest;
}
