package com.watson.business.house.domain.entity;

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
@Table(name = "house_files")
public class HouseFile extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    private String fileName;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void setHouse(House house) {
        this.house = house;
    }

    public HouseFile(String fileName) {
        this.fileName = fileName;
    }
    public void deleteHouseFile() {
        this.isDeleted = true;
    }
}
