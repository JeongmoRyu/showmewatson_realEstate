package com.watson.business.house.domain.entity;

import com.watson.business.house.domain.entity.houseContractInfoDetail.MonthlyInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.SaleInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.YearlyInfo;
import com.watson.business.house.dto.item.STATUS;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "houses")
public class House extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    private String realtorId;
    private int contractCode;
    private String courtCode;
    private int houseCode;
    private double squareMeter;
    private int floor;
    private int totalFloor;
    private String address;
    private String title;
    private String content;
    private double supplyAreaMeter;
    private String buildingUse;
    private String approvalBuildingDate;
    private int bathroom;
    private int room;
    private String maintenanceList;

    private int weeklyViewCount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private STATUS status;

    @OneToMany(mappedBy = "house")
    private List<HouseFile> houseFiles;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HouseOption houseOption;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SaleInfo saleInfo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private YearlyInfo yearlyInfo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MonthlyInfo monthlyInfo;

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public void setYearlyInfo(YearlyInfo yearlyInfo) {
        this.yearlyInfo = yearlyInfo;
    }

    public void setMonthlyInfo(MonthlyInfo monthlyInfo) {
        this.monthlyInfo = monthlyInfo;
    }

    public void addHouseFile(final HouseFile houseFile) {
        houseFiles.add(houseFile);
        houseFile.setHouse(this);
    }

    public void removeImage(final HouseFile houseFile){
        houseFiles.remove(houseFile);
        houseFile.setHouse(null);
    }
    public void editHouse(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
