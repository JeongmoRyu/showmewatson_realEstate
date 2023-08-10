package com.watson.business.personalfilter.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFilterResponse {
    Long filterId;
    int houseCode;      //  방 유형(all)
    int contractCode;   //  계약 유형(all)
    String courtCode;
    //    면적(all)
    double minSquareMeter;
    double maxSquareMeter;
    //    보증금(월세, 전세)
    Long minDeposit;
    Long maxDeposit;
    //    관리비(월세, 전세)
    int minMaintenance;
    int maxMaintenance;
    //    월세(월세)
    Long minMonthlyRent;
    Long maxMonthlyRent;
    //    매매가(매매)
    Long minSalePrice;
    Long maxSalePrice;
}
