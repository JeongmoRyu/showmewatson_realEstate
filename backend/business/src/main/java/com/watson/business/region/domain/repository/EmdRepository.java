package com.watson.business.region.domain.repository;

import com.watson.business.region.domain.entity.Emd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmdRepository extends JpaRepository<Emd, String> {
//    @Query(value = "select distinct gungu_name from business.emd where sido_name = :sido and gungu_name is not null", nativeQuery = true)
//    List<String> findGunguNamesBySido(@Param("sido") String sido);
//
//    @Query(value = "SELECT DISTINCT court_code, donglee_name  FROM business.emd WHERE gungu_name = :gunguName AND NOT dong_name IS NULL", nativeQuery = true)
//    List<Object[]> findCourtCodesByGungu(@Param("gunguName") String gunguName);
//
//    @Query("SELECT DISTINCT e.dongleeName FROM Emd e WHERE e.courtCode = :courtCode")
//    String findDongleeNameByCourtCode(@Param("courtCode") String courtCode);

}
