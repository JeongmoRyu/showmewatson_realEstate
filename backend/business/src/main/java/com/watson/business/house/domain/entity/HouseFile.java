package com.watson.business.house.domain.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class HouseFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    @JsonBackReference
    private House house;

    @Column(nullable = false)
    private String fileName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date regDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    public HouseFile() {

    }

    public void setHouse(House house) {
        this.house = house;
    }

    @PrePersist
    protected void onCreate() {
        regDate = new Date();
    }

    public HouseFile(String fileName) {
        this.fileName = fileName;
    }
}
