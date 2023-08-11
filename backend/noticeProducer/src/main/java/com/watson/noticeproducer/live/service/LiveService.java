package com.watson.noticeproducer.live.service;

import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.watson.noticeproducer.kafka.ProducerService;
import com.watson.noticeproducer.live.domain.entity.LiveSchedule;
import com.watson.noticeproducer.live.domain.entity.NoticeUser;
import com.watson.noticeproducer.live.domain.entity.NotificationInfo;
import com.watson.noticeproducer.live.domain.repository.LiveScheduleRepository;
import com.watson.noticeproducer.live.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final ProducerService producerService;
    private final NotificationRepository notificationRepository;
    private final LiveScheduleRepository liveScheduleRepository;
    @Scheduled(cron = "0 0,30 * * * *")     // 정각과 30분마다 라이브 시작 알림
    public void liveStartNotification() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");   // 현재 시간에서 날짜/시/분만 추출

        String formattedDateTime = currentDateTime.format(formatter);

        Optional<NotificationInfo> notificationInfoOptional = notificationRepository.findById(formattedDateTime);   // 해당 시간에 대한 NotificationInfo 가져오기
        if (!notificationInfoOptional.isEmpty()) {
            Set<NoticeUser> users = notificationInfoOptional.get().getUsers();

            HashMap<String, ArrayList<String>> messageMap = new HashMap<>();

            for(NoticeUser user : users) {
                String liveSchedulesId = user.getLiveSchedulesId();
                String fcmToken = user.getFcmToken();

                if (messageMap.containsKey(liveSchedulesId)) {
                    messageMap.get(liveSchedulesId).add(fcmToken);
                } else {
                    ArrayList<String> fcmTokens = new ArrayList<>();
                    fcmTokens.add(fcmToken);
                    messageMap.put(liveSchedulesId, fcmTokens);
                }
            }

            for (Map.Entry<String, ArrayList<String>> entry : messageMap.entrySet()) {
                String liveSchedulesId = entry.getKey();
                ArrayList<String> fcmTokens = entry.getValue();

                Gson gson = new Gson();

                LiveSchedule liveSchedule = liveScheduleRepository.findLiveScheduleById(Long.valueOf(liveSchedulesId));
                String muticastMessageJson = gson.toJson(createMessage(liveSchedulesId, liveSchedule.getContent(), fcmTokens));
                log.info("muticastMessageJson: {}", muticastMessageJson);
                producerService.sendToBroker("live", muticastMessageJson);      // live 토픽에 전송
            }

            liveScheduleRepository.deleteById(Long.valueOf(formattedDateTime));
        }
    }

    // 메시지 생성
    private MulticastMessage createMessage(String liveSchedulesId, String liveSchedulesContent, ArrayList<String> fcmTokens) {
        String noticeHead = "라이브 알림";
        log.info("start live: {}", liveSchedulesId);
        Notification notification = Notification.builder()
                .setTitle(noticeHead)
                .setBody(liveSchedulesContent).build();

        return MulticastMessage.builder()
                .addAllTokens(fcmTokens) // FCM registration tokens 리스트
                .setNotification(notification)
                .build();
    }
}
