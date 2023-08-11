package com.watson.auth.admin.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserLoginResponse {

    @NonNull
    private String accessToken;

    @NonNull
    @Builder.Default
    private String role = "User";

}
