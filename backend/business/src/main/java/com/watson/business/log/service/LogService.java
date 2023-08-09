package com.watson.business.log.service;

import com.watson.business.log.domain.entity.ViewLog;
import com.watson.business.log.domain.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;


    public List<ViewLog> findAllLog() {
        return logRepository.findAll();
    }

    public void insertViewLog(Long houseId, String userId, String dongleeName) {
        LocalDate today = LocalDate.now();
        boolean alreadyViewd = logRepository.existsByUserIdAndHouseIdAndLogDate(userId, houseId, today);
        if (alreadyViewd) return;
        ViewLog viewLog = ViewLog.builder()
                .userId(userId)
                .dongleeName(dongleeName)
                .houseId(houseId)
                .build();
        logRepository.save(viewLog); // 엔티티 삽입
    }
}
