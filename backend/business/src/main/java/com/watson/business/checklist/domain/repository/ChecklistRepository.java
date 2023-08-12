package com.watson.business.checklist.domain.repository;

import com.watson.business.checklist.domain.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
	Checklist findByUserIdAndHouseId(String userId, Long houseId);
}
