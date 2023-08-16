package com.watson.business.checklist.service;

import com.watson.business.checklist.domain.entity.Checklist;
import com.watson.business.checklist.domain.repository.ChecklistRepository;
import com.watson.business.checklist.dto.ChecklistRequest;
import com.watson.business.checklist.dto.ChecklistResponse;
import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
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
		try {
			Checklist checklist = checklistRepository.findByUserIdAndHouseId(userId, houseId);
			return new ChecklistResponse(checklist.getCheckListItem());
		} catch (Exception e) {
			throw new HouseException(HouseErrorCode.FAIL_FOUND_CHECKLIST);
		}
	}

	@Override
	public Long addChecklist(String userId, ChecklistRequest request) {
		try {
			Checklist list = checklistRepository.save(Checklist.builder()
					.userId(userId)
					.houseId(request.getHouseId())
					.checkListItem(request.getChecklist())
					.build());
			return list.getId();
		} catch (Exception e) {
			throw new HouseException(HouseErrorCode.FAIL_REGIST_CHECKLIST);
		}
	}
}
