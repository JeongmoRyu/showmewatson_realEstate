package com.watson.business.checklist.controller;

import com.watson.business.checklist.dto.ChecklistRequest;
import com.watson.business.checklist.dto.ChecklistResponse;
import com.watson.business.checklist.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/checklist")
@RequiredArgsConstructor
public class ChecklistController {
	private final ChecklistService checklistService;

	private static String userId = "admin";
	@GetMapping("{house_id}")
	public ResponseEntity<ChecklistResponse> cheklistList(@PathVariable("house_id") Long houseId) {
//      oauth server : access token -> return userId
		return ResponseEntity.status(HttpStatus.OK).body(checklistService.findChecklist(userId, houseId));
	}
	@PostMapping("")
	public ResponseEntity<String> checklistAdd(@RequestBody ChecklistRequest checklistRequest) {
		//  oauth server : access token -> return userId
		checklistService.addChecklist(userId, checklistRequest);
		return ResponseEntity.status(HttpStatus.OK).body("체크리스트 등록 완료");
	}

}
