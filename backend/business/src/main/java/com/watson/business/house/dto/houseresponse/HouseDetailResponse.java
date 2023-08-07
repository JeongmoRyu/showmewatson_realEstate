package com.watson.business.house.dto.houseresponse;

import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.house.dto.item.STATUS;
import com.watson.business.region.dto.EmdResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDetailResponse {
    private String sidoName;
    private String gunguName;
    private String dongleeName;

    private Long houseId;
    private int houseCode;
    private double squareMeter;
    private double suppleAreaMeter;
    private int floor;
    private String address;

    private Long deposit;
    private Long monthlyRent;
    private Long salePrice;
    private int maintenance;

    private String title;
    private STATUS status;

    private String fileName;

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
    private List<HouseFile> houseFiles;
    private LocalDateTime regDate;
    private HouseOption houseOption;

    public void setHouseListResponse(HouseListResponse list) {
        this.houseId = list.getHouseId();
        this.houseCode = list.getHouseCode();
        this.squareMeter = list.getSquareMeter();
        this.suppleAreaMeter = list.getSuppleAreaMeter();
        this.floor = list.getFloor();
        this.address = list.getAddress();
        this.deposit = list.getDeposit();
        this.monthlyRent = list.getMonthlyRent();
        this.salePrice = list.getSalePrice();
        this.maintenance = list.getMaintenance();
        this.title = list.getTitle();
        this.status = list.getStatus();
        this.fileName = list.getFileName();
        this.sidoName = list.getSidoName();
        this.gunguName = list.getGunguName();
        this.dongleeName = list.getDongleeName();
    }
}

