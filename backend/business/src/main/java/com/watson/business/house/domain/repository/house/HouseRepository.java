package com.watson.business.house.domain.repository.house;

import com.watson.business.house.domain.entity.house.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

}
