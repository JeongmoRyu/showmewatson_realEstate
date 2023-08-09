package com.watson.business.house.dto.houserequest;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRegistRequest {

    private int contractCode;
    private String courtCode;
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
