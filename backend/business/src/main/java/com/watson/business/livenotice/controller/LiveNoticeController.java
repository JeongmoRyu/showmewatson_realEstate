package com.watson.business.livenotice.controller;

import com.watson.business.connect.service.ConnectAuthService;
import com.watson.business.exception.HouseException;
import com.watson.business.livenotice.dto.LiveAlarmResponse;
import com.watson.business.livenotice.server.LiveNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/live-notice")
@Slf4j
public class LiveNoticeController {
    private final LiveNoticeService liveNoticeService;
    private final ConnectAuthService connectAuthService;
    // 2차 알림 등록
    @PostMapping("")
    public ResponseEntity<String> registLiveNotice(@RequestHeader("Authorization") String accessToken, @RequestBody String liveSchedulesId) {
        liveNoticeService.registLiveNotice(liveSchedulesId, connectAuthService.getFcmToken(accessToken));
        return ResponseEntity.status(HttpStatus.OK).body("라이브 알림을 등록했습니다.");
    }

    // 2차 알림 취소
    @PutMapping("")
    public ResponseEntity<String> deleteLiveNotice(@RequestHeader("Authorization") String accessToken, @RequestBody String liveSchedulesId) {
        liveNoticeService.cancelLiveNotice(liveSchedulesId, connectAuthService.getFcmToken(accessToken));
        return ResponseEntity.status(HttpStatus.OK).body("라이브 알림을 취소했습니다.");
    }

    // 2차 알림 리스트
    @GetMapping("")
    public ResponseEntity<List<LiveAlarmResponse>> getLiveNotice(@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(liveNoticeService.getLiveNoticeList(connectAuthService.getFcmToken(accessToken)));
    }
}
