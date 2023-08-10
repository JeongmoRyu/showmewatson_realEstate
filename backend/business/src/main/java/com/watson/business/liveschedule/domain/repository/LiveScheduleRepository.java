package com.watson.business.liveschedule.domain.repository;

import com.watson.business.liveschedule.domain.entity.LiveSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LiveScheduleRepository extends JpaRepository<LiveSchedule, Long> {
    LiveSchedule findLiveScheduleById(Long id);

    @Query("SELECT ls.liveDate FROM LiveSchedule ls WHERE ls.id = :id")
    Date findLiveDateById(Long id);
}
