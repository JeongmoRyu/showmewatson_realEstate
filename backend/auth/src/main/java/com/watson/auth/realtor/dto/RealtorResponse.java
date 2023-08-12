package com.watson.auth.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RealtorResponse {

    // 중개사 정보
    private String realtorName;
    private String phoneNumber;
    private String profileImg;

    // 사무소 정보
    private String registId;
    private String agencyName;

}