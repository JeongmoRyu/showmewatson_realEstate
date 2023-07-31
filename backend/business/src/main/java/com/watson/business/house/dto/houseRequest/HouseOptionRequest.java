package com.watson.business.house.dto.houseRequest;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
