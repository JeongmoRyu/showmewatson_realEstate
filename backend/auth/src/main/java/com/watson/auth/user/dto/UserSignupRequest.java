package com.watson.auth.user.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserSignupRequest {

    @NonNull
    private String authId;

    @Enumerated(EnumType.STRING)
    @NonNull
    private String authType;

    @NonNull
    private String nickname;

    @NonNull
    private String password; // Security로 로그인하기 위해 패스워드 설정

}