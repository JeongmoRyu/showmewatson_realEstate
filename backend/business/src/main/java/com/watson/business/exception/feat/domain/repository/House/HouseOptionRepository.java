package com.watson.business.exception.feat.domain.repository.House;

import com.watson.business.exception.feat.domain.entity.House.HouseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseOptionRepository extends JpaRepository<HouseOption, Long> {

}
