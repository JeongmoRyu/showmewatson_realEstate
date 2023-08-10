package com.watson.auth.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> authRedisTemplate;

    /* Set */
    public void setValues(String authId, String refreshToken) {
        ValueOperations<String, String> values = authRedisTemplate.opsForValue();
        values.set(authId, refreshToken, Duration.ofMinutes(5)); // 5분 후 메모리에서 삭제
    }

    /* Get */
    public String getValues(String authId) {
        ValueOperations<String, String> values = authRedisTemplate.opsForValue();

        try {
            return values.get(authId).toString();
        } catch (Exception e) {
            log.info("Refresh Token이 만료되어 로그인이 필요합니다.");
            e.printStackTrace();
            return "index";
        }
    }

}
