package com.watson.business.exception.feat.dto.houseregist;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HouseOptionRequest {
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
