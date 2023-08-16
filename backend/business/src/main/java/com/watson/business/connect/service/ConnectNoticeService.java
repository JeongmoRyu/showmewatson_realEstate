package com.watson.business.connect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ConnectNoticeService {
    private final RestTemplate restTemplate = new RestTemplate();

    // 라이브 등록
    public ResponseEntity<String> noticeLive(String houseId, String title) {

        String targetServerUrl = "http://notice-producer:8082/send/"+houseId+"/"+title;

        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();
        return  restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }
}
