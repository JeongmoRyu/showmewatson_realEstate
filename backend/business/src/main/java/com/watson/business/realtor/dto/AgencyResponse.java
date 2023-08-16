package com.watson.business.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AgencyResponse {
    /* 사무소 정보 */
    String agencyName;
    String address;
    String tel;
    String agencyImg;

    /* 중개사 정보 */
    String realtorId;
    String realtorName;
    String phoneNumber;
}
