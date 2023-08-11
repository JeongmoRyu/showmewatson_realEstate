package com.watson.auth.admin.controller;

import com.watson.auth.admin.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/redis")
@RestController
public class RedisController { // Refresh Token api Test 용

    private final RedisService redisService;

    @PostMapping
    public void redisCreateTest(@RequestBody HashMap<String, String> body) {
        redisService.setValues(body.get("authId"), body.get("refreshToken"));
    }

    @GetMapping
    public String redisReadTest(@RequestParam String authId) {
        return redisService.getValues(authId);
    }

}