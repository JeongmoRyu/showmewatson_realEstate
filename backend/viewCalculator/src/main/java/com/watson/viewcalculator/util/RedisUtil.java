package com.watson.viewcalculator.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
@EnableRedisRepositories
public class RedisUtil {
    @Value("${spring.redis.rank.host}")
    private String redisHost;

    @Value("${spring.redis.rank.port}")
    private int redisPort;

    @Value("${spring.redis.rank.sentinel.master}")
    private String rankSentinelMaster;

    @Value("${spring.redis.rank.sentinel.nodes}")
    private List<String> rankSentinelNodes;

    @Value("${spring.redis.rank.password}")
    private String redisPassword;

    @Bean(name = "rankRedisConnectionFactory")
    public RedisConnectionFactory rankRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);
        org.springframework.data.redis.connection.RedisSentinelConfiguration redisSentinelConfiguration = new org.springframework.data.redis.connection.RedisSentinelConfiguration()
                .master(rankSentinelMaster)
                .sentinel(redisHost, 26379)
                .sentinel(redisHost, 26380)
                .sentinel(redisHost, 26381);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.rankRedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
