package com.watson.viewcalculator;

import com.watson.viewcalculator.viewcount.domain.repository.ViewCountRepository;
import com.watson.viewcalculator.viewcount.service.ViewCountService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ViewCalculatorApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(ViewCalculatorApplicationTests.class);
    @MockBean
    private ViewCountRepository viewCountRepository;
    @Autowired
    private ViewCountService viewCountService;
    @Autowired
    private RedisTemplate<String,String> rankRedisTemplate;

    @Test
    void testRedisConnection(){
        String key = "testKey";
        String value = "testValue";

        // 데이터 저장
        rankRedisTemplate.opsForValue().set(key, value);

        // 데이터 조회
        String retrievedValue = rankRedisTemplate.opsForValue().get(key);
        System.out.println("Retrieved Value: " + retrievedValue);

        // 테스트 어설션 추가
        assert  retrievedValue.equals(value);
    }
}
