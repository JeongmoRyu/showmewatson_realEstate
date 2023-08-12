package com.watson.business.checklist.service;

import com.watson.business.checklist.domain.entity.Checklist;
import com.watson.business.checklist.domain.repository.ChecklistRepository;
import com.watson.business.checklist.dto.ChecklistRequest;
import com.watson.business.checklist.dto.ChecklistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChecklistServiceImp implements ChecklistService {
	private final ChecklistRepository checklistRepository;

	@Override
	public ChecklistResponse findChecklist(String userId, Long houseId) {
		Checklist checklist = checklistRepository.findByUserIdAndHouseId(userId, houseId);
		return new ChecklistResponse(checklist.getCheckListItem());
	}

	@Override
	public Long addChecklist(String userId, ChecklistRequest request) {
		Checklist list = checklistRepository.save(Checklist.builder()
				.userId(userId)
				.houseId(request.getHouseId())
				.checkListItem(request.getChecklist())
				.build());
		return list.getId();
	}
}
