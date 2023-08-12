package com.watson.business.checklist.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChecklistRequest {
	private Long houseId;
	private List<Integer> checklist;
}
