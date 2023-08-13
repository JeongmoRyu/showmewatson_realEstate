package com.watson.business.house.dto.houseresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseOptionResponse {
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
