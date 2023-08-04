package com.watson.log.domain.repository;

import com.watson.log.domain.entity.ViewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<ViewLog,Long> {
//    @Query("SELECT DISTINCT l FROM ViewLog l " +
//            "WHERE l.houseId=:houseId " +
//            "AND l.userId = :userId " +
//            "AND l.logDate = :logDate")
//    List<ViewLog> findByHouseIdAndUserIdAndLogDate(
//            @Param("houseId") Long houseId,
//            @Param("userId") Long userId,
//            @Param("logDate") Date logDate
//    );
}
