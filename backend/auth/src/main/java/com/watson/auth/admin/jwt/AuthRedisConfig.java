package com.watson.auth.admin.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;


@Configuration
@EnableRedisRepositories
public class AuthRedisConfig {

    @Value("${spring.redis.auth.host}")
    private String redisHost;

    @Value("${spring.redis.auth.port}")
    private int redisPort;

    @Value("${spring.redis.auth.sentinel.master}")
    private String authSentinelMaster;

    @Value("${spring.redis.auth.sentinel.nodes}")
    private List<String> authSentinelNodes;

    @Value("${spring.redis.auth.password}")
    private String redisPassword;

    @Bean(name = "houseRedisConnectionFactory")
    public RedisConnectionFactory authRedisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);

        org.springframework.data.redis.connection.RedisSentinelConfiguration redisSentinelConfiguration = new org.springframework.data.redis.connection.RedisSentinelConfiguration()
                .master(authSentinelMaster)
                .sentinel(redisHost, 26379)
                .sentinel(redisHost, 26380)
                .sentinel(redisHost, 26381)
                ;

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(name = "authRedisTemplate")
    public RedisTemplate<String, String> authRedisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.authRedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}

