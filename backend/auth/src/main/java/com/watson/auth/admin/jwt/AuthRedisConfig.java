package com.watson.auth.admin.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class AuthRedisConfig {
     @Value("${spring.redis.auth.sentinel.master}")
    private String authSentinelMaster;

     @Value("${spring.redis.auth.sentinel.nodes}")
    private List<String> authSentinelNodes;

     @Bean(name = "authRedisConnectionFactory")
     public RedisConnectionFactory authRedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master(authSentinelMaster);

        authSentinelNodes.forEach(s -> sentinelConfig.sentinel(s.split(":")[0],
                Integer.valueOf(s.split(":")[1])));

        return new LettuceConnectionFactory(sentinelConfig);
    }

    @Bean(name = "authRedisTemplate")
    public RedisTemplate<String, String> authRedisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(authRedisConnectionFactory());

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.setConnectionFactory(authRedisConnectionFactory());
        return redisTemplate;
    }
}
