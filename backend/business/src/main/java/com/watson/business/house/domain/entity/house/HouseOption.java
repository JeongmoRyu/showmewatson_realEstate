package com.watson.business.house.domain.entity.house;

import com.watson.business.house.dto.houseregist.HouseOptionRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class HouseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private boolean sink;

    @Column(nullable = false)
    private boolean airConditioner;

    @Column(nullable = false)
    private boolean shoeCloset;

    @Column(nullable = false)
    private boolean washingMachine;

    @Column(nullable = false)
    private boolean refrigerator;

    @Column(nullable = false)
    private boolean closet;

    @Column(nullable = false)
    private boolean induction;

    @Column(nullable = false)
    private boolean desk;

    @Column(nullable = false)
    private boolean elevator;

    @Column(nullable = false)
    private boolean coldHeating;

    @Column(nullable = false)
    private boolean parking;


    public HouseOption() {

    }
    public HouseOption(HouseOptionRequest houseOptionRequest) {
        this.sink = houseOptionRequest.isSink();
        this.airConditioner = houseOptionRequest.isAirConditioner();
        this.shoeCloset = houseOptionRequest.isShoeCloset();
        this.washingMachine = houseOptionRequest.isWashingMachine();
        this.refrigerator = houseOptionRequest.isRefrigerator();
        this.closet = houseOptionRequest.isCloset();
        this.induction = houseOptionRequest.isInduction();
        this.desk = houseOptionRequest.isDesk();
        this.elevator = houseOptionRequest.isElevator();
        this.coldHeating = houseOptionRequest.isColdHeating();
        this.parking = houseOptionRequest.isParking();
    }


}
