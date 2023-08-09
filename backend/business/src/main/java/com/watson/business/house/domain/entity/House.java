package com.watson.business.house.domain.entity;

import com.watson.business.house.domain.entity.housecontractinfodetail.MonthlyInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.SaleInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.YearlyInfo;
import com.watson.business.house.dto.item.STATUS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Long id;

    @Column(name = "realtor_id")
    private String realtorId;

    @Column(name = "contract_code")
    private int contractCode;

    @Column(name = "court_code")
    private String courtCode;

    @Column(name = "house_code")
    private int houseCode;

    @Column(name = "square_meter")
    private double squareMeter;

    @Column(name = "floor")
    private int floor;

    @Column(name = "total_floor")
    private int totalFloor;

    @Column(name = "address")
    private String address;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "supply_area_meter")
    private double supplyAreaMeter;

    @Column(name = "building_use")
    private String buildingUse;

    @Column(name = "approval_building_date")
    private String approvalBuildingDate;

    @Column(name = "bathroom")
    private int bathroom;

    @Column(name = "room")
    private int room;
    @Column(name = "maintenance_list")
    private String maintenanceList;

    @Column(name = "weekly_view_count")
    private int weeklyViewCount;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private STATUS status;

    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "house")
    private List<HouseFile> houseFiles;

    @JoinColumn(name = "house_option_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HouseOption houseOption;

    @JoinColumn(name = "sale_info_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SaleInfo saleInfo;

    @JoinColumn(name = "yearly_info_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private YearlyInfo yearlyInfo;

    @JoinColumn(name = "monthly_info_id")
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

    public void removeImage(final HouseFile houseFile) {
        houseFiles.remove(houseFile);
        houseFile.setHouse(null);
    }

    public void editHouse(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
