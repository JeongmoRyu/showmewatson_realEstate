package com.watson.business.house.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "house_options")
public class HouseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sink")
    private boolean sink;

    @Column(name = "air_conditioner")
    private boolean airConditioner;

    @Column(name = "shoe_closet")
    private boolean shoeCloset;

    @Column(name = "washing_machine")
    private boolean washingMachine;

    @Column(name = "refrigerator")
    private boolean refrigerator;

    @Column(name = "closet")
    private boolean closet;

    @Column(name = "induction")
    private boolean induction;

    @Column(name = "desk")
    private boolean desk;

    @Column(name = "elevator")
    private boolean elevator;

    @Column(name = "cold_heating")
    private boolean coldHeating;

    @Column(name = "parking")
    private boolean parking;
}
