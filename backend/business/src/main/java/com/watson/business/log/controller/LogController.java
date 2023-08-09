package com.watson.business.log.controller;

import com.watson.business.log.domain.entity.ViewLog;
import com.watson.business.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {
    private final LogService logService;
    @GetMapping
    public ResponseEntity<List<ViewLog>> logList() {
        return ResponseEntity.status(HttpStatus.OK).body(logService.findAllLog());
    }
}
