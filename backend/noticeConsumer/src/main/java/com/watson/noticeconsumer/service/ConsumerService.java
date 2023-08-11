package com.watson.noticeconsumer.service;

import com.google.firebase.messaging.MulticastMessage;
import com.google.gson.Gson;
import com.watson.noticeconsumer.configure.KafkaProperties;
import com.watson.noticeconsumer.exception.FCMException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final NotificationsService notificationsService;

    @KafkaListener(topics = {"wish", "live"}, groupId = KafkaProperties.CONSUMER_GROUP_ID)
    public void consumerMessage(String message) {
        log.info("Received Message : {}", message);
        Gson gson = new Gson();
        try {
            notificationsService.sendMessage(gson.fromJson(message, MulticastMessage.class));
        } catch (FCMException e) {
            log.info("FCMException: {}", e.getMessage());
        }
    }

}
