package com.watson.auth.admin.jwt;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshTokenRequest {
    private String refreshToken;

    // 생성자, getter, setter 생략
}