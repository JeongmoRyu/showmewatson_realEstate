package com.watson.business.house.domain.entity.housecontractinfodetail;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monthly_infos")
public class MonthlyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long deposit;
    private Long monthlyRent;
    private int maintenance;
    private String maintenanceList;
}
