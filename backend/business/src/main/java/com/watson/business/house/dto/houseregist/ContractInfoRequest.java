package com.watson.business.house.dto.houseregist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class ContractInfoRequest {
    private Long salePrice;
    private Long deposit;
    private Long monthlyRent;
    private int maintenance;
    private String maintenanceList;

    // 매매
    public ContractInfoRequest(Long salePrice) {
        this.salePrice = salePrice;
    }

    // 월세
    public ContractInfoRequest(Long deposit, Long monthlyRent, int maintenance, String maintenanceList) {
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.maintenance = maintenance;
        this.maintenanceList = maintenanceList;
    }

    // 전세
    public ContractInfoRequest(Long deposit, int maintenance, String maintenanceList) {
        this.deposit = deposit;
        this.maintenance = maintenance;
        this.maintenanceList = maintenanceList;
    }
}
