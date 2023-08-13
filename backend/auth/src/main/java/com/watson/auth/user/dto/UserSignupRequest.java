package com.watson.auth.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserSignupRequest {

    private String nickname;
    private String authId; // authId
    private String fcmToken;

}