package com.watson.noticeproducer.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.watson.noticeproducer.exception.FCMException;
import com.watson.noticeproducer.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final WishService wishService;

    @ExceptionHandler(FCMException.class)
    public ResponseEntity<String> handleCustomServiceException(FirebaseMessagingException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @GetMapping(value = "/send/{houseId}/{title}")
    public @ResponseBody ResponseEntity<String> send(@PathVariable Long houseId, String title) throws FCMException{
        wishService.sendLiveNotice(houseId, title);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}