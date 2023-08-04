package com.watson.auth.jwt;

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