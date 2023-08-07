package com.watson.business.livenotice.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LiveNoticeService {
    private final RedisTemplate<String, Object> noticeRedisTemplate;

    public LiveNoticeService(RedisTemplate<String, Object> noticeRedisTemplate) {
        this.noticeRedisTemplate = noticeRedisTemplate;
        log.info("noticeRedisTemplate: {}", noticeRedisTemplate);
    }

    public void registLiveNotice(String houseId, String fcmToken) {
        putLiveNotice(houseId, fcmToken, false);
    }

    public void cancelLiveNotice(String houseId, String fcmToken) {
        putLiveNotice(houseId, fcmToken, true);
    }

    // 실제 Redis 서버에 저장
    private void putLiveNotice(String houseId, String fcmToken, Boolean isDeleted) {
        HashOperations<String, Object, Object> hashOps = noticeRedisTemplate.opsForHash();
        hashOps.put(houseId, fcmToken, isDeleted);
    }

    public Object getLiveNotice(String houseId, String fcmToken) {
        HashOperations<String, Object, Object> hashOps = noticeRedisTemplate.opsForHash();
        return hashOps.get(houseId, fcmToken);
    }
}
