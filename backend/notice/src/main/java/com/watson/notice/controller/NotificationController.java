package com.watson.notice.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.watson.notice.service.NotificationsService;
import com.watson.notice.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final WishService wishService;
    private final NotificationsService androidPushNotificationsService;

    @GetMapping(value = "/send/{houseId}/{title}")
    public @ResponseBody ResponseEntity<String> send(@PathVariable Long houseId, String title) throws FirebaseMessagingException {
        int successCount = wishService.sendLiveNotice(houseId, title);

        return new ResponseEntity<>(successCount+" messages were sent successfully", HttpStatus.OK);
    }
}