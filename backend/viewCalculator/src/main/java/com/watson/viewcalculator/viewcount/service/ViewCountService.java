package com.watson.viewcalculator.viewcount.service;

import com.watson.viewcalculator.viewcount.domain.entity.ViewLog;
import com.watson.viewcalculator.viewcount.domain.repository.ViewCountRepository;
import com.watson.viewcalculator.viewcount.dto.HashMapCustomValue;
import com.watson.viewcalculator.viewcount.dto.LogDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class ViewCountService {
    private final ViewCountRepository viewCountRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ZSetOperations<String, Object> zSetOperations;

    public ViewCountService(ViewCountRepository viewCountRepository, RedisTemplate<String, Object> redisTemplate) {
        this.viewCountRepository = viewCountRepository;
        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    //하루 전, 모든 로그 조회
    public List<LogDto> getLogsFromYesterday() {
        return viewCountRepository.findLogByLogDate(LocalDate.now().minusDays(1));
    }

    //모든 Log
    public List<ViewLog> getAllLog() {
        return viewCountRepository.findAll();
    }

    //하루 전 로그들을 바탕으로 조회수 레디스에 기록
    public void saveDailyViewCountToRedis() {
        List<LogDto> list = getLogsFromYesterday();
        Map<Long, HashMapCustomValue> dailyViewCountMap = new HashMap<>();

        //모든 로그 조회하면서 houseId당 조회수 계산
        for (LogDto logDto : list) {
            String dongleeName = logDto.getDongleeName();
            Long houseId = logDto.getHouseId();
            LocalDate logDate = logDto.getLogDate();

            dailyViewCountMap.compute(houseId, (key, oldValue) ->
                    oldValue == null
                            ? new HashMapCustomValue(logDate, dongleeName, 1L)
                            : new HashMapCustomValue(oldValue.getLogDate(), oldValue.getDongleeName(), oldValue.getViewCount() + 1)
            );

        }
        //houseId별 조회수를 바탕으로 Redis에 저장
        for (Map.Entry<Long, HashMapCustomValue> entry : dailyViewCountMap.entrySet()) {
            Long houseId = entry.getKey();
            HashMapCustomValue tmp = entry.getValue();
            String dongleeName = tmp.getDongleeName();
            String logDate = tmp.getLogDate().toString();
            Long viewCount = tmp.getViewCount();
            String zSetName = "daily:viewCount:" + dongleeName + ":" + logDate;
            redisTemplate.opsForZSet().add(zSetName, houseId.toString(), viewCount);
            redisTemplate.expire(zSetName, 8, TimeUnit.DAYS);
        }

    }

    public List<String> getWeeklyRankByDongleeName(String dongleeName) {
        List<String> zSetKeys = new ArrayList<>();
        IntStream.rangeClosed(1, 7)
                .mapToObj(i -> LocalDate.now().minusDays(i))
                .map(date -> "daily:viewCount:" + dongleeName + ":" + date)
                .forEach(zSetKeys::add);

        String tmpKey = "weekly:temp" + dongleeName;

        zSetOperations.unionAndStore(tmpKey, zSetKeys, tmpKey);

        List<String> top5List = new ArrayList<>();
        Set<Object> top5Set = zSetOperations.reverseRange(tmpKey, 0, 4);

        if (top5Set == null) return new ArrayList<>();
        top5Set.forEach(house -> top5List.add(house.toString()));

        return top5List;
    }
}
