package com.watson.business.house.dto.houserequest;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseFilterParamRequest {
    int houseCode;      //  방 유형(all)
    int contractCode;   //  계약 유형(all)
//    String dongCode;    //  동 코드(all) : 보류. 프론트와 소통 후 진행 방향 결정

    //    면적(all)
    int minSquareMeter;
    int maxSquareMeter;
    //    보증금(월세, 전세)
    Long minDeposit;
    Long maxDeposit;
    //    관리비(월세, 전세)
    Long minMaintenance;
    Long maxMaintenance;
    //    월세(월세)
    Long minMonthlyRent;
    Long maxMonthlyRent;
    //    매매가(매매)
    Long minSalePrice;
    Long maxSalePrice;
}
