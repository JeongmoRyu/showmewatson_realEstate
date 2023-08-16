package com.watson.business.connect.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.realtor.dto.RealtorInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.StringTokenizer;

@Slf4j
@Service
public class ConnectAuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASEURL = "http://auth:8080/auth";
    // 사용자 아이디 가져오기 (유효성 검사)
    public String getUserId(String accessToken) {
        if (accessToken == null || accessToken.equals("")) return null;
        String targetServerUrl = BASEURL+"/admin/get-userid/"+accessToken;

        log.debug(targetServerUrl);
        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                if (responseBody == null || responseBody.isEmpty()) {
                    return null; // If the response body is empty or null, return null
                }

                StringTokenizer st = new StringTokenizer(responseBody, "_");
                if (st.nextToken().equals("u")) {
                    return responseBody;
                }
            } else {
                return null; // If the response status is 500, return null
            }
        } catch (HttpServerErrorException e) {
            return null;
        }
        return null;
    }
    // 중개사 아이디 가져오기 (유효성 검사)
    public String getRealtorId(String accessToken) {
        try {
            if (accessToken == null || accessToken.equals("")) {
                throw new HouseException(HouseErrorCode.NOT_REALTOR_USER);
            }

            String targetServerUrl = BASEURL+"/admin/get-userid/"+accessToken;
            String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                    .toUriString();

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                StringTokenizer st = new StringTokenizer(response.getBody(), "_");
                if (st.nextToken().equals("r")) {
                    return response.getBody();
                } else {
                    throw new HouseException(HouseErrorCode.NOT_REALTOR_USER);
                }
            } else {
                throw new HouseException(HouseErrorCode.NOT_REALTOR_USER);
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new HouseException(HouseErrorCode.NOT_REALTOR_USER);
        }
    }


    // 사용자 FcmToken 가져오기
    public String getFcmToken(String accessToken) {
        try {
            if (accessToken == null || accessToken.equals("")) {
                throw new HouseException(HouseErrorCode.NOT_FOUND_FCMTOKEN);
            }

            String targetServerUrl = BASEURL+"/admin/get-fcm-token/"+accessToken;
            String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                    .toUriString();

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            log.info("response: {}", response);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                log.info("get-fcm-token: {}", response.getBody());
                return response.getBody();
            }

            throw new HouseException(HouseErrorCode.NOT_FOUND_FCMTOKEN);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_FCMTOKEN);
        }
    }


    // FcmToken 저장
    public ResponseEntity<String> saveFcmToken(String accessToken, String fcmToken) {
        String targetServerUrl = BASEURL+"/admin/save-fcm-token/"+accessToken+"/"+fcmToken;

        String url = UriComponentsBuilder.fromHttpUrl(targetServerUrl)
                .toUriString();

        return restTemplate.exchange(url, HttpMethod.POST, null, String.class);
    }

    // 중개사 정보 가져오기
    public RealtorInfoResponse getRealtorDetail(String realtorId) {
        String targetServerUrl = BASEURL+"/realtor/"+realtorId;
        ResponseEntity<RealtorInfoResponse> response = restTemplate.getForEntity(targetServerUrl, RealtorInfoResponse.class);
        log.info("response: {}", response);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new HouseException(HouseErrorCode.NOT_FOUND_REALTOR);
    }
}
