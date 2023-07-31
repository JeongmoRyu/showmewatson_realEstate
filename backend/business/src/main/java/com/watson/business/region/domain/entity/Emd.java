package com.watson.business.region.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Emd {
    @Id
    private String adminDongCode;

    @Column
    private String sidoName;

    @Column
    private String gunguName;

    @Column
    private String dongName;

    @Column
    private String courtCode;

    @Column
    private String dongleeName;

    @Column
    private String createdAt;

    @Column
    private String deletedAt;
}
