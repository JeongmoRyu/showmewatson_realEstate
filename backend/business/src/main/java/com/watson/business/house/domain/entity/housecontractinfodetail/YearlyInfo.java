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
@Table(name = "yearly_infos")
public class YearlyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "maintenance")
    private int maintenance;

    @Column(name = "maintenance_list")
    private String maintenanceList;
}
