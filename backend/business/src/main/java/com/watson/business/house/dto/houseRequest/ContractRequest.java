package com.watson.business.house.dto.houseRequest;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {
    private Long salePrice;
    private Long deposit;
    private Long monthlyRent;
    private int maintenance;
    private String maintenanceList;

    // 매매
    public ContractRequest(Long salePrice) {
        this.salePrice = salePrice;
    }

    // 월세
    public ContractRequest(Long deposit, Long monthlyRent, int maintenance, String maintenanceList) {
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.maintenance = maintenance;
        this.maintenanceList = maintenanceList;
    }

    // 전세
    public ContractRequest(Long deposit, int maintenance, String maintenanceList) {
        this.deposit = deposit;
        this.maintenance = maintenance;
        this.maintenanceList = maintenanceList;
    }
}
