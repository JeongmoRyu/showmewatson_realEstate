package com.watson.business.region.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="emd")
public class Emd {
    @Id
    @Column(name = "admin_dong_code")
    private String adminDongCode;

    @Column(name = "sido_name")
    private String sidoName;

    @Column(name = "gungu_name")
    private String gunguName;

    @Column(name = "dong_name")
    private String dongName;

    @Column(name = "court_code")
    private String courtCode;

    @Column(name = "donglee_name")
    private String dongleeName;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "deleted_at")
    private String deletedAt;

}
