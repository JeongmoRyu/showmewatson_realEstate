package com.watson.business.house.domain.repository;

import com.watson.business.house.domain.entity.HouseFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseFileRepository extends JpaRepository<HouseFile, Long> {
	List<HouseFile> findHouseFileByHouseId(Long id);
}
