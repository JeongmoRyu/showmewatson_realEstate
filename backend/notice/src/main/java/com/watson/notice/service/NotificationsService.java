package com.watson.notice.service;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class NotificationsService {

    @Value("${fcm.key.path}")
    private String fcmPrivateKeyPath;

    @Value("${fcm.key.scope}")
    private String fireBaseScope;

    // fcm 기본 설정
    @PostConstruct
    public void init() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(
                        GoogleCredentials
                                .fromStream(new ClassPathResource(fcmPrivateKeyPath).getInputStream())
                                .createScoped(List.of(fireBaseScope)))
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

}