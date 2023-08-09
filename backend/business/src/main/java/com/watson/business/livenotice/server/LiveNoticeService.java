package com.watson.business.livenotice.server;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.liveschedule.domain.repository.LiveScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class LiveNoticeService {
    private final RedisTemplate<String, Object> noticeRedisTemplate;
    private final LiveScheduleRepository liveScheduleRepository;

    public void registLiveNotice(String houseId, String fcmToken) {
        try {
            Date liveDate = liveScheduleRepository.findLiveDateByHouseId(Long.valueOf(houseId));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            String formattedDate = sdf.format(liveDate);

            log.info("liveDate: {}", formattedDate);

            addValueToSet(formattedDate, fcmToken+","+houseId);
        } catch (NullPointerException e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_LIVE_INFO);
        }
    }
    public void cancelLiveNotice(String houseId, String fcmToken) {
        try {
            Date liveDate = liveScheduleRepository.findLiveDateByHouseId(Long.valueOf(houseId));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            String formattedDate = sdf.format(liveDate);

            deleteValueFromSet(formattedDate, fcmToken+","+houseId);
        } catch (NullPointerException e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_LIVE_INFO);
        }
    }

    public Long addValueToSet(String key, String value) {
        return noticeRedisTemplate.opsForSet().add(key, value);
    }

    public Long deleteValueFromSet(String key, String value) {
        return noticeRedisTemplate.opsForSet().remove(key, value);
    }
}
