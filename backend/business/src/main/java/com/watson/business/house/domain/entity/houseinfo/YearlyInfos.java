package com.watson.business.house.domain.entity.houseinfo;

import com.watson.business.house.dto.houseregist.ContractInfoRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class YearlyInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private int maintenance;

    @Column(nullable = false)
    private String maintenanceList;

    public YearlyInfos() {
    }

    public YearlyInfos(Long deposit, int maintenance, String maintenanceList) {
        this.deposit = deposit;
        this.maintenance = maintenance;
        this.maintenanceList = maintenanceList;
    }

    public YearlyInfos(ContractInfoRequest contractInfo) {
        this.deposit = contractInfo.getDeposit();
        this.maintenance = contractInfo.getMaintenance();
        this.maintenanceList = contractInfo.getMaintenanceList();
    }
}
