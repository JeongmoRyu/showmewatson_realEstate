package com.watson.business.wish.dto;

import lombok.Data;

@Data
public class WishRequest {
    private Long houseId;
    private String fcmToken;
}
