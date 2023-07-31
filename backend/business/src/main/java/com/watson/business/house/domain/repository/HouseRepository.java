package com.watson.business.house.domain.repository;

import com.watson.business.house.domain.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("SELECT DISTINCT h FROM House h JOIN FETCH h.houseFiles f WHERE h.status != 'DELETED' AND f.isDeleted = false")
    List<House> findHouses();

    @Query("SELECT DISTINCT h FROM House h JOIN FETCH h.houseFiles f WHERE h.id = :houseId AND f.isDeleted = false")
    House findHouseById(@Param("houseId") Long houseId);


}
