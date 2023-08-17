package com.watson.viewcalculator.viewcount.controller;

import com.watson.viewcalculator.viewcount.dto.LogDto;
import com.watson.viewcalculator.viewcount.service.ViewCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ViewCountController {
    private final ViewCountService viewCountService;

    @GetMapping("log")
    public ResponseEntity<List<LogDto>> getLog(){
        return ResponseEntity.status(HttpStatus.OK).body(viewCountService.getLogsFromYesterday());
    }

    @GetMapping("save-log")
    public ResponseEntity<HttpStatus> saveLogToRedis() {
        viewCountService.saveDailyViewCountToRedis();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("weekly-rank/{dongleeName}")
    public ResponseEntity<List<String>> getWeeklyRankByDongleeName(@PathVariable String dongleeName){
        return ResponseEntity.status(HttpStatus.OK).body(viewCountService.getWeeklyRankByDongleeName(dongleeName));
    }
}
