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

    private boolean sink;
    private boolean airConditioner;
    private boolean shoeCloset;
    private boolean washingMachine;
    private boolean refrigerator;
    private boolean closet;
    private boolean induction;
    private boolean desk;
    private boolean elevator;
    private boolean coldHeating;
    private boolean parking;
}
