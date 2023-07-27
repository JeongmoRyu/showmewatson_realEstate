package com.watson.business.exception.feat.domain.repository.House;

import com.watson.business.exception.feat.domain.entity.House.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

}
