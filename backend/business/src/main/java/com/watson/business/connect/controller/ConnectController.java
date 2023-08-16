package com.watson.business.connect.controller;

import com.watson.business.connect.service.ConnectAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/save-token")
@RequiredArgsConstructor
public class ConnectController {
    private final ConnectAuthService connectAuthService;

    @PostMapping()
    public ResponseEntity<String> saveFcmToken(@RequestHeader("Authorization") String accessToken, @RequestBody String fcmToken) {
        return connectAuthService.saveFcmToken(accessToken, fcmToken);
    }
}
