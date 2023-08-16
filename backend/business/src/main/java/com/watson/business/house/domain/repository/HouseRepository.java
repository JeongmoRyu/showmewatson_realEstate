package com.watson.business.house.domain.repository;

import com.watson.business.house.domain.entity.House;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
    House findHouseById(Long houseId);
    List<House> findAll(Specification<House> spec);
    @Query("SELECT DISTINCT h FROM House h LEFT JOIN FETCH h.houseFiles")
    List<House> findAllHousesWithFiles();

    @Query("SELECT DISTINCT h FROM House h LEFT JOIN FETCH h.houseFiles where h.realtorId = :realtorId")
    List<House> findAllHousesByRealtorIdWithFiles(@Param("realtorId") String realtorId);
}
