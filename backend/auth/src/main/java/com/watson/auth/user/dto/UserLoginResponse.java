package com.watson.auth.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserLoginResponse {

    @NonNull
    private String accessToken;

    @NonNull
    private String refreshToken;

}
