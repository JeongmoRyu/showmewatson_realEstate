package com.watson.business.liveschedule.controller;

import com.watson.business.connect.service.ConnectAuthService;
import com.watson.business.connect.service.ConnectNoticeService;
import com.watson.business.liveschedule.dto.LiveScheduleRequest;
import com.watson.business.liveschedule.dto.LiveScheduleResponse;
import com.watson.business.liveschedule.service.LiveScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/schedule")
@RequiredArgsConstructor
public class LiveScheduleController {
    private final LiveScheduleService liveScheduleService;
    private final ConnectAuthService connectAuthService;
    private final ConnectNoticeService connectNoticeService;
    @GetMapping("")
    public ResponseEntity<List<LiveScheduleResponse>> liveScheduleList() {
        return ResponseEntity.status(HttpStatus.OK).body(liveScheduleService.findAllLiveSchedules());
    }
    @GetMapping("{schedule_id}")
    public ResponseEntity<LiveScheduleResponse> liveScheduleDetailByScheduleId(@PathVariable("schedule_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(liveScheduleService.findLiveScheduleByScheduleId(id));
    }
    @PostMapping("")
    public ResponseEntity<String> liveScheduleDetailByScheduleId(@RequestHeader("Authorization") String accessToken, @RequestBody @Valid LiveScheduleRequest requset) {
        String realtorId = connectAuthService.getRealtorId(accessToken);
        requset.setRealtorId(realtorId);

        liveScheduleService.addLiveSchedule(requset);
        ResponseEntity<String> response = connectNoticeService.noticeLive(String.valueOf(requset.getHouseId()), requset.getContent());
        log.info("발송: {}" , response.getStatusCode());

        return ResponseEntity.status(HttpStatus.OK).body("라이브 공지 등록 성공");
    }
}
