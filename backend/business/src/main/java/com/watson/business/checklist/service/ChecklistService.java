package com.watson.business.checklist.service;

import com.watson.business.checklist.dto.ChecklistRequest;
import com.watson.business.checklist.dto.ChecklistResponse;

public interface ChecklistService {
	ChecklistResponse findChecklist(String userId, Long houseId);
	Long addChecklist(String userId, ChecklistRequest request);
}
