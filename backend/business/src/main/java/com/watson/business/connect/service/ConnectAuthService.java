package com.watson.business.connect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class ConnectAuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASEURL = "http://auth:8080/auth";
    // 사용자 아이디 가져오기 (유효성 검사)
    public ResponseEntity<String> getUserId(String accessToken) {

        String targetServerUrl = BASEURL+"/admin/get-userid/"+accessToken;

        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    // 사용자 FcmToken 가져오기
    public ResponseEntity<String> getFcmToken(String accessToken) {
        String targetServerUrl = BASEURL+"/admin/get-fcm-token/"+accessToken;

        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    // FcmToken 저장
    public ResponseEntity<String> saveFcmToken(String accessToken, String fcmToken) {
        String targetServerUrl = BASEURL+"/admin/save-fcm-token/"+accessToken+"/"+fcmToken;

        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.POST, null, String.class);
    }

    // 중개사 정보 가져오기
    public ResponseEntity<String> getRealtorDetail(String realtorId) {
        String targetServerUrl = BASEURL+"/realtor/"+realtorId;

        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }
}
