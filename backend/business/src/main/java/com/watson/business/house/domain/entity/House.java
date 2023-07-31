package com.watson.business.house.domain.entity;

import com.watson.business.house.domain.entity.houseinfo.MonthlyInfos;
import com.watson.business.house.domain.entity.houseinfo.SaleInfos;
import com.watson.business.house.domain.entity.houseinfo.YearlyInfos;
import com.watson.business.house.dto.item.STATUS;
import com.watson.business.house.dto.houseregist.HouseRegistRequest;
import javax.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String realtorId;

    /**
     * 1: 월세
     * 2: 전세
     * 3: 매매
     */
    @Column(nullable = false)
    private int contractCode;

    @Column(nullable = false)
    private String dongCode;

    /**
     * 1: 원룸
     * 2: 투룸
     * 3: 쓰리룸 이상
     */
    @Column(nullable = false)
    private int houseCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date regDate;

    @Column(nullable = false)
    private double squareMeter;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private int totalFloor;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT TRADING")
    private STATUS status;

    @Column(nullable = false)
    private int weeklyViewCount;

    @Column(nullable = false)
    private double supplyAreaMeter;

    @Column(nullable = false)
    private String buildingUse;

    @Column(nullable = false)
    private String approvalBuildingDate;

    @Column(nullable = false)
    private int bathroom;

    @OneToOne(cascade = CascadeType.ALL)
    private HouseOption houseOption;

    @OneToOne(cascade = CascadeType.ALL)
    private SaleInfos saleInfos;

    @OneToOne(cascade = CascadeType.ALL)
    private YearlyInfos yearlyInfos;

    @OneToOne(cascade = CascadeType.ALL)
    private MonthlyInfos monthlyInfos;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HouseFile> houseFiles= new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        regDate = new Date(); // 현재 시간을 할당
    }

    public House() {
    }

    public House(String realtorId, HouseRegistRequest houseRegistRequest, HouseOption houseOption) {
        this.realtorId = realtorId;
        this.contractCode = houseRegistRequest.getContractCode();
        this.dongCode = houseRegistRequest.getDongCode();
        this.houseCode = houseRegistRequest.getHouseCode();
        this.squareMeter = houseRegistRequest.getSquareMeter();
        this.floor = houseRegistRequest.getFloor();
        this.totalFloor = houseRegistRequest.getTotalFloor();
        this.address = houseRegistRequest.getAddress();
        this.title = houseRegistRequest.getTitle();
        this.content = houseRegistRequest.getContent();
        this.weeklyViewCount = 0;
        this.supplyAreaMeter = houseRegistRequest.getSupplyAreaMeter();
        this.buildingUse = houseRegistRequest.getBuildingUse();
        this.approvalBuildingDate = houseRegistRequest.getApprovalBuildingDate();
        this.bathroom = houseRegistRequest.getBathroom();
        this.houseOption = houseOption;
    }

    public void setSaleInfos(SaleInfos saleInfos) {
        this.saleInfos = saleInfos;
    }

    public void setYearlyInfos(YearlyInfos yearlyInfos) {
        this.yearlyInfos = yearlyInfos;
    }

    public void setMonthlyInfos(MonthlyInfos monthlyInfos) {
        this.monthlyInfos = monthlyInfos;
    }

    public void addHouseFile(final HouseFile houseFile) {
        houseFiles.add(houseFile);
        houseFile.setHouse(this);
    }

    public void removeImage(final HouseFile houseFile){
        houseFiles.remove(houseFile);
        houseFile.setHouse(null);
    }
}
