package com.watson.business.connect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class ConnectNoticeService {
    private final RestTemplate restTemplate = new RestTemplate();

    // 라이브 등록
    public ResponseEntity<String> noticeLive(String houseId, String title) {
        String targetServerUrl = "http://notice-producer:8082/send/{houseId}/{title}";

        // URL 파라미터를 인코딩하여 적용
        String encodedTitle = UriUtils.encode(title, StandardCharsets.UTF_8);
        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .buildAndExpand(houseId, encodedTitle)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }
}
