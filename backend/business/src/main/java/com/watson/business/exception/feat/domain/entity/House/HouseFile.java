package com.watson.business.exception.feat.domain.entity.House;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class HouseFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date regDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private String isDeleted;

    @PrePersist
    protected void onCreate() {
        regDate = new Date(); // 현재 시간을 할당합니다.
    }
}
