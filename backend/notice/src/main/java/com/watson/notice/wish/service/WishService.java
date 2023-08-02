package com.watson.notice.wish.service;

import com.google.firebase.messaging.*;
import com.watson.notice.wish.domain.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public int sendLiveNotice(Long houseId, String title) throws FirebaseMessagingException {
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

            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
            return response.getSuccessCount();
        }
        return 0;
    }
}
