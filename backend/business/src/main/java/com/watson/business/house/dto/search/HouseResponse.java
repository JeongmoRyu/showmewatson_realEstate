package com.watson.business.house.dto.search;

import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.house.dto.item.STATUS;
import com.watson.business.region.dto.EmdResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class HouseResponse {
    private RealtorResponse realtor;
    private Long houseId;
    private Long deposit;
    private Long monthlyRent;
    private int maintenance;
    private String maintenanceList;
    private Long salePrice;
    private int contractCode;
    private EmdResponse emdResponse;
    private int houseCode;
    private double suppleAreaMeter;
    private double squareMeter;
    private int floor;
    private int totalFloor;
    private String address;
    private String buildingUse;
    private String approvalBuildingDate;
    private int bathroom;
    private String title;
    private String content;
    private STATUS status;
    private List<HouseFile> houseFiles;
    private Date regDate;
    private HouseOption houseOption;
    private boolean isWished;

    public HouseResponse(RealtorResponse realtor, Long houseId, int contractCode, EmdResponse emdResponse, int houseCode, double suppleAreaMeter, double squareMeter, int floor, int totalFloor, String address, String buildingUse, String approvalBuildingDate, int bathroom, String title, String content, STATUS status, List<HouseFile> houseFiles, Date regDate, HouseOption houseOption) {
        this.realtor = realtor;
        this.houseId = houseId;
        this.contractCode = contractCode;
        this.emdResponse = emdResponse;
        this.houseCode = houseCode;
        this.suppleAreaMeter = suppleAreaMeter;
        this.squareMeter = squareMeter;
        this.floor = floor;
        this.totalFloor = totalFloor;
        this.address = address;
        this.buildingUse = buildingUse;
        this.approvalBuildingDate = approvalBuildingDate;
        this.bathroom = bathroom;
        this.title = title;
        this.content = content;
        this.status = status;
        this.houseFiles = houseFiles;
        this.regDate = regDate;
        this.houseOption = houseOption;
    }
}

