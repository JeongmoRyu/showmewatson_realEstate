package com.watson.business.livenotice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
public class NoticeRedisConfig {

    @Value("${spring.redis.notice.sentinel.master}")
    private String noticeSentinelMaster;

    @Value("${spring.redis.notice.sentinel.nodes}")
    private List<String> noticeSentinelNodes;

    @Bean(name = "noticeRedisConnectionFactory")
    public RedisConnectionFactory noticeRedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master(noticeSentinelMaster);

        noticeSentinelNodes.forEach(s -> sentinelConfig.sentinel(s.split(":")[0],
                Integer.valueOf(s.split(":")[1])));

        return new LettuceConnectionFactory(sentinelConfig);
    }


    @Bean(name = "noticeRedisTemplate")
    public RedisTemplate<String, Object> noticeRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(noticeRedisConnectionFactory());
        return redisTemplate;
    }
}