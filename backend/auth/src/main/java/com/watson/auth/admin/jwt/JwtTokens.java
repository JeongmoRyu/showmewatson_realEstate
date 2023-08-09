package com.watson.auth.admin.jwt;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtTokens {

    private String accessToken;
    private String refreshToken;

}