package com.watson.auth.realtor.domain.entity;

import com.watson.auth.util.BaseEntity;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name="realtors")
public class Realtor extends BaseEntity {

    @Id
    @GeneratedValue // 테스트하고 지우기
    private String id;

    @ManyToOne
    @JoinColumn(name="agency_id")
    private Agency agency;

    @Column(nullable = false)
    private String realtorName;

    @Column(nullable = false)
    private String license;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String profileImg;

}