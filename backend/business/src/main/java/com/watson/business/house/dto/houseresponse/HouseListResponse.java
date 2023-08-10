package com.watson.business.house.dto.houseresponse;

import com.watson.business.house.dto.item.STATUS;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseListResponse {
    private Long id;
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

    private String fileName;

    private boolean isWished;
//    시도, 구군, 동리
    private String sidoName;
    private String gunguName;
    private String dongleeName;
}
