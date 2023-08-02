package com.watson.business.house.domain.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "view_counts")
public class ViewCounts {
    @Id
    private Long id;
//    @Id
//    private Long houseId;
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id")
//    private House house;
//
//    private int mon;
//    private int tue;
//    private int wed;
//    private int thu;
//    private int fri;
//    private int sat;
//    private int sun;

}
