package com.watson.auth.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AgencyResponse {

    /* 사무소 정보 */
    String agencyName;
    String address;
    String tel;

    /* 중개사 정보 */
    String realtorId;
    String realtorName;

    /* 등록 매물 */
//    List<String> houseId;
//    List<String> houseName;

}