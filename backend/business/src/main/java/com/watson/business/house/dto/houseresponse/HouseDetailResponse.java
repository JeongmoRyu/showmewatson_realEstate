package com.watson.business.house.dto.houseresponse;

import com.watson.business.house.dto.item.STATUS;
import com.watson.business.region.dto.EmdResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseDetailResponse {
    private String sidoName;
    private String gunguName;
    private String dongleeName;

    private Long houseId;
    private int houseCode;
    private double squareMeter;
    private double supplyAreaMeter;
    private int floor;
    private String address;

    private Long deposit;
    private Long monthlyRent;
    private Long salePrice;
    private int maintenance;

    private String title;
    private STATUS status;

    private List<String> fileNames;

    private RealtorResponse realtor;

    private String maintenanceList;
    private int contractCode;
    private EmdResponse emdResponse;
    private int totalFloor;
    private String buildingUse;
    private String approvalBuildingDate;
    private int bathroom;
    private int room;

    private String content;
    private LocalDateTime regDate;

    private HouseOptionResponse houseOption;

    public void setHouseListResponse(HouseListResponse list) {
        this.houseId = list.getId();
        this.houseCode = list.getHouseCode();
        this.squareMeter = list.getSquareMeter();
        this.supplyAreaMeter = list.getSupplyAreaMeter();
        this.floor = list.getFloor();
        this.address = list.getAddress();
        this.deposit = list.getDeposit();
        this.monthlyRent = list.getMonthlyRent();
        this.salePrice = list.getSalePrice();
        this.maintenance = list.getMaintenance();
        this.title = list.getTitle();
        this.status = list.getStatus();
        this.fileNames = list.getFileNames();
        this.sidoName = list.getSidoName();
        this.gunguName = list.getGunguName();
        this.dongleeName = list.getDongleeName();
    }

    public void setHouseOption(HouseOptionResponse option) {
        this.houseOption = option;
    }
}

