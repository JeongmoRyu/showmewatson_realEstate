package com.watson.business.liveschedule.domain.repository;

import com.watson.business.liveschedule.domain.entity.LiveSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveScheduleRepository extends JpaRepository<LiveSchedule, Long> {
    LiveSchedule findLiveScheduleById(Long id);

    @Query("SELECT ls.liveDate FROM LiveSchedule ls WHERE ls.id = :id")
    String findLiveDateById(Long id);
}
