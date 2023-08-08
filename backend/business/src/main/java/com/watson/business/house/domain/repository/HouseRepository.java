package com.watson.business.house.domain.repository;

import com.watson.business.house.domain.entity.House;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
    House findHouseById(Long houseId);
    List<House> findAll(Specification<House> spec);
}
