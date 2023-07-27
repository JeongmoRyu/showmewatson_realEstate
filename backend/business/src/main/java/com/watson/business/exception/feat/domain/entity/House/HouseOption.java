package com.watson.business.exception.feat.domain.entity.House;

import com.watson.business.exception.feat.dto.houseregist.HouseOptionRequest;
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

    public HouseOption(boolean sink, boolean airConditioner, boolean shoeCloset, boolean washingMachine, boolean refrigerator, boolean closet, boolean induction, boolean desk, boolean elevator, boolean coldHeating, boolean parking) {
        this.sink = sink;
        this.airConditioner = airConditioner;
        this.shoeCloset = shoeCloset;
        this.washingMachine = washingMachine;
        this.refrigerator = refrigerator;
        this.closet = closet;
        this.induction = induction;
        this.desk = desk;
        this.elevator = elevator;
        this.coldHeating = coldHeating;
        this.parking = parking;
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
