package com.watson.auth.admin.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class LoginResponse {

    private String accessToken;
    private String role;

}
