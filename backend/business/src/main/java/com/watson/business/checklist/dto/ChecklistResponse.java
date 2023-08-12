package com.watson.business.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChecklistResponse {
	private List<Integer> checklist;
}