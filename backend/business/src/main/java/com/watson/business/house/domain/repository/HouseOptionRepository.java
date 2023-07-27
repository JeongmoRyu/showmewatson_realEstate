package com.watson.business.house.domain.repository;

import com.watson.business.house.domain.entity.HouseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseOptionRepository extends JpaRepository<HouseOption, Long> {

}
