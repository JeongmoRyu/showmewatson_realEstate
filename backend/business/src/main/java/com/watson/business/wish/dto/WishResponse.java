package com.watson.business.wish.dto;

import com.watson.business.house.dto.item.STATUS;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishResponse {
    private Long houseId;
    private int houseCode;
    private double squareMeter;
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
