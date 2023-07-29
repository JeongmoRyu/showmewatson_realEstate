package com.watson.business.house.dto.search;

import com.watson.business.house.dto.item.STATUS;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HouseListResponse {
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
    private String content;
    private STATUS status;
    private String fileName;
    private boolean isWished;

    public HouseListResponse(Long houseId, int houseCode, double squareMeter, double suppleAreaMeter, int floor, String address, String content, STATUS status) {
        this.houseId = houseId;
        this.houseCode = houseCode;
        this.squareMeter = squareMeter;
        this.suppleAreaMeter = suppleAreaMeter;
        this.floor = floor;
        this.address = address;
        this.content = content;
        this.status = status;
    }
}
