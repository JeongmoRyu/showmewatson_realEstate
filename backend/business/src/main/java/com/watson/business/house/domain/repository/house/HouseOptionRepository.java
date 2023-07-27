package com.watson.business.house.domain.repository.house;

import com.watson.business.house.domain.entity.house.HouseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseOptionRepository extends JpaRepository<HouseOption, Long> {

}
