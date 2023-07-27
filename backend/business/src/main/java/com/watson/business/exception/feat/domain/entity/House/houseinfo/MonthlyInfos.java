package com.watson.business.exception.feat.domain.entity.House.houseinfo;

import com.watson.business.exception.feat.dto.houseregist.ContractInfoRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class MonthlyInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private Long monthlyRent;

    @Column(nullable = false)
    private int maintenance;

    @Column(nullable = false)
    private String maintenanceList;

    public MonthlyInfos() {

    }
    public MonthlyInfos(Long deposit, Long monthlyRent, int maintenance, String maintenanceList) {
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.maintenance = maintenance;
        this.maintenanceList = maintenanceList;
    }

    public MonthlyInfos(ContractInfoRequest contractInfo) {
        this.deposit = contractInfo.getDeposit();
        this.monthlyRent = contractInfo.getMonthlyRent();
        this.maintenance = contractInfo.getMaintenance();
        this.maintenanceList = contractInfo.getMaintenanceList();
    }
}
