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
    public void setValues(String authId, String tokens) {
        try {
            ValueOperations<String, String> values = authRedisTemplate.opsForValue();
            values.set(authId, tokens, Duration.ofDays(365)); // RefreshToken expire 1년
            log.info("Redis에 Access Token과 Refresh Token을 등록합니다. : " + tokens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Get */
    public String getValues(String authId) {
        ValueOperations<String, String> values = authRedisTemplate.opsForValue();

        try {
            return values.get(authId);
        } catch (Exception e) {
            log.info("Refresh Token이 만료되어 로그인이 필요합니다.");
            e.printStackTrace();
            return "index";
        }
    }

    /* Update */
    public void updateValues(String authId, String tokens) {
        ValueOperations<String, String> values = authRedisTemplate.opsForValue();

        try {
            values.getAndDelete(authId);
            log.info("authId가 " + authId + "인 Token 정보를 삭제합니다.");
            values.set(authId, tokens, Duration.ofDays(365)); // RefreshToken expire 1년
            log.info("Redis에 새로운 Access Token과 Refresh Token을 등록합니다. : " + tokens);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
