package com.watson.business.checklist.controller;

import com.watson.business.checklist.dto.ChecklistRequest;
import com.watson.business.checklist.dto.ChecklistResponse;
import com.watson.business.checklist.service.ChecklistService;
import com.watson.business.connect.service.ConnectAuthService;
import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/checklist")
@RequiredArgsConstructor
public class ChecklistController {
	private final ChecklistService checklistService;
	private final ConnectAuthService connectAuthService;

	@GetMapping("{house_id}")
	public ResponseEntity<ChecklistResponse> cheklistList(@RequestHeader("Authorization") String accessToken, @PathVariable("house_id") Long houseId) {
		String userId = connectAuthService.getUserId(accessToken);
		if (userId != null) {
			return ResponseEntity.status(HttpStatus.OK).body(checklistService.findChecklist(userId, houseId));
		} else {
			throw new HouseException(HouseErrorCode.NOT_REALTOR_USER);
		}
	}
	@PostMapping("")
	public ResponseEntity<String> checklistAdd(@RequestHeader("Authorization") String accessToken, @RequestBody ChecklistRequest checklistRequest) {
		String userId = connectAuthService.getUserId(accessToken);
		if (userId != null) {
			checklistService.addChecklist(userId, checklistRequest);
			return ResponseEntity.status(HttpStatus.OK).body("체크리스트 등록 완료");
		}else {
			throw new HouseException(HouseErrorCode.NOT_REALTOR_USER);
		}
	}

}
