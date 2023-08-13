package com.watson.auth.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RealtorSignupRequest {

    // 중개사 정보
    private String id;
    private String license;
    private String realtorName;
    private String phoneNumber;
    private String authType;
    private String authId;

    // 사무소 정보
    private String registId;
    private String agencyName;
    private String address;
    private String tel;

    // 기기정보
    private String fcmToken;

}