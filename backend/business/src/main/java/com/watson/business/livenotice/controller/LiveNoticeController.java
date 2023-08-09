package com.watson.business.livenotice.controller;

import com.watson.business.exception.HouseException;
import com.watson.business.livenotice.server.LiveNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/live-notice")
@Slf4j
public class LiveNoticeController {
    private final LiveNoticeService liveNoticeService;
    // 2차 알림 등록
    @PostMapping("")
    public ResponseEntity<String> registLiveNotice(@RequestBody String houseId) {
        // TODO: header에서 받은 access-token을 이용해서 fcmToken 받아오기 (현재 임시값)
        String fcmToken = "cj6aI7U9T8Kk7gJo7ktADP:APA91bEnfl6eG8_x2XNXX-i0O8HINpqM9tWoeQG3rxMeKp-FzffVZvVKAsDr206K0csWczmtKTw6fm1Tj3VGNlGZ_VHim9P5dCwDlsw_FGc2MSy8_IBneaEr1CyqD_HKLISs_Cl0QI5B";
        try {
            liveNoticeService.registLiveNotice(houseId, fcmToken);
        } catch (HouseException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("라이브 알림을 등록했습니다.");
    }

    // 2차 알림 취소
    @PutMapping("")
    public ResponseEntity<String> deleteLiveNotice(@RequestBody String houseId) {
        // TODO: header에서 받은 access-token을 이용해서 fcmToken 받아오기 (현재 임시값)
        String fcmToken = "cj6aI7U9T8Kk7gJo7ktADP:APA91bEnfl6eG8_x2XNXX-i0O8HINpqM9tWoeQG3rxMeKp-FzffVZvVKAsDr206K0csWczmtKTw6fm1Tj3VGNlGZ_VHim9P5dCwDlsw_FGc2MSy8_IBneaEr1CyqD_HKLISs_Cl0QI5B";
        try {
            liveNoticeService.cancelLiveNotice(houseId, fcmToken);
        } catch (HouseException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("라이브 알림을 취소했습니다.");
    }

}
