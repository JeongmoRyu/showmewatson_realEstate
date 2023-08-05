package com.watson.noticeproducer.wish.service;

import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.watson.noticeproducer.kafka.ProducerService;
import com.watson.noticeproducer.wish.domain.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;
    private final ProducerService producerService;

    public void sendLiveNotice(Long houseId, String title){
        List<String> wishFcmTokenList = wishRepository.findFcmTokenByHouseIdAndIsDeleted(houseId);

        if (!wishFcmTokenList.isEmpty()) {
            String noticeHead = "새로운 라이브 공지";
            Notification notification = Notification.builder()
                    .setTitle(noticeHead)
                    .setBody(title).build();
            MulticastMessage multicastMessage = MulticastMessage.builder()
                    .addAllTokens(wishFcmTokenList) // FCM registration tokens 리스트
                    .setNotification(notification)
                    .build();

            Gson gson = new Gson();
            String muticastMessageJson = gson.toJson(multicastMessage);
            producerService.sendToBroker("wish", muticastMessageJson);
        }
    }
}
