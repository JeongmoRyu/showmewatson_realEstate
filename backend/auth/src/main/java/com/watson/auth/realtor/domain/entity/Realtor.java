package com.watson.auth.realtor.domain.entity;

import com.watson.auth.util.BaseEntity;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Table(name="realtors")
public class Realtor extends BaseEntity {

    @Id
    private String id;

    // 중개사 정보
    @Column(nullable = false)
    private String license;

    @Column(nullable = false)
    private String realtorName;

    @Column(nullable = false)
    private String phoneNumber;

    // 중개사무소 정보
    @Column(nullable = false)
    private String registId;

    @Column(nullable = false)
    private String agencyName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String agencyImg;

}