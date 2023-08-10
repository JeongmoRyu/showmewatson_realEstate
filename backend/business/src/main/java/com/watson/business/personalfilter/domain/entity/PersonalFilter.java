package com.watson.business.personalfilter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personal_filters")
public class PersonalFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "house_code")
    private int houseCode;      //  방 유형(all)

    @Column(name = "contract_code")
    private int contractCode;   //  계약 유형(all)

    @Column(name = "court_code")
    private String courtCode;

    //    면적(all)
    @Column(name = "min_square_meter")
    private double minSquareMeter;

    @Column(name = "max_square_meter")
    private double maxSquareMeter;

    //    보증금(월세, 전세)
    @Column(name = "min_deposit")
    private Long minDeposit;

    @Column(name = "max_deposit")
    private Long maxDeposit;

    //    관리비(월세, 전세)
    @Column(name = "min_maintenance")
    private int minMaintenance;

    @Column(name = "max_maintenance")
    private int maxMaintenance;

    //    월세(월세)
    @Column(name = "min_monthly_rent")
    private Long minMonthlyRent;

    @Column(name = "max_monthly_rent")
    private Long maxMonthlyRent;

    //    매매가(매매)
    @Column(name = "min_sale_price")
    private Long minSalePrice;

    @Column(name = "max_sale_price")
    private Long maxSalePrice;
}
