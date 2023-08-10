package com.watson.business.wish.dto;

import lombok.Data;

@Data
public class WishesRequest {
    private Long houseId;
    private String fcmToken;
}
