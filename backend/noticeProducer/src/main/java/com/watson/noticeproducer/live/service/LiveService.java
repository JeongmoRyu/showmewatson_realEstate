package com.watson.noticeproducer.live.service;

import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.watson.noticeproducer.kafka.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@EnableScheduling
@Service
@RequiredArgsConstructor
@Slf4j
public class LiveService {
    private final RedisTemplate<String, Object> noticeRedisTemplate;
    private final ProducerService producerService;

    @Scheduled(cron = "0 0,30 * * * *")     // 정각과 30분마다 라이브 시작 알림
    public void liveStartNotification() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");   // 현재 시간에서 날짜/시/분만 추출

        String formattedDateTime = currentDateTime.format(formatter);

        Set<Object> objects = readValuesFromSet(formattedDateTime);

        if (!objects.isEmpty()) {
            Map<String, ArrayList<String>> houseIdByFcmToken = parseObject(objects);

            for (Map.Entry<String, ArrayList<String>> entry : houseIdByFcmToken.entrySet()) {
                String houseId = entry.getKey();
                ArrayList<String> fcmTokens = entry.getValue();

                Gson gson = new Gson();
                String muticastMessageJson = gson.toJson(createMessage(houseId, fcmTokens));
                log.info("muticastMessageJson: {}", muticastMessageJson);
                producerService.sendToBroker("live", muticastMessageJson);      // live 토픽에 전송
            }
        }
    }

    // 메시지 생성
    private MulticastMessage createMessage(String houseId, ArrayList<String> fcmTokens) {
        String noticeHead = "라이브 알림";
        String title = "라이브가 시작되었습니다.";
        log.info("start live: {}", houseId);
        Notification notification = Notification.builder()
                .setTitle(noticeHead)
                .setBody(title).build();

        return MulticastMessage.builder()
                .addAllTokens(fcmTokens) // FCM registration tokens 리스트
                .setNotification(notification)
                .build();
    }

    // 캐시에서 읽은 값 파싱
    public Map<String, ArrayList<String>> parseObject(Set<Object> objects) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (Object value : objects) {
            String stringValue = (String) value;
            String[] parts = stringValue.split(",");
            String fcmToken = parts[0];
            String houseId = parts[1];

            if (map.containsKey(houseId)) {
                map.get(houseId).add(fcmToken);
            } else {
                ArrayList<String> fcmTokens = new ArrayList<>();
                fcmTokens.add(fcmToken);
                map.put(houseId, fcmTokens);
            }
        }
        return map;
    }

    public Set<Object> readValuesFromSet(String key) {
        return noticeRedisTemplate.opsForSet().members(key);
    }

}
