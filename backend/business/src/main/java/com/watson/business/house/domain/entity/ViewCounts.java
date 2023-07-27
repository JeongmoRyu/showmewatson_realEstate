package com.watson.business.house.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class ViewCounts {
    @Id
    private Long houseId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private House house;

    @Column
    private int mon;

    @Column
    private int tue;

    @Column
    private int wed;

    @Column
    private int thu;

    @Column
    private int fri;

    @Column
    private int sat;

    @Column
    private int sun;

    public ViewCounts(House house) {
        this.house = house;
        this.mon = 0;
        this.tue = 0;
        this.wed = 0;
        this.thu = 0;
        this.fri = 0;
        this.sat = 0;
        this.sun = 0;
    }

    public ViewCounts() {

    }
}
