package com.watson.viewcalculator.viewcount.domain.repository;

import com.watson.viewcalculator.viewcount.domain.entity.ViewLog;
import com.watson.viewcalculator.viewcount.dto.LogDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViewCountRepository extends JpaRepository<ViewLog, Long> {
    @Query("SELECT new com.watson.viewcalculator.viewcount.dto.LogDto(l.userId, l.houseId, l.logDate, l.dongleeName) FROM ViewLog l " +
            "WHERE l.logDate = :day ")
    List<LogDto> findLogByLogDate(@Param("day") LocalDate day);
}