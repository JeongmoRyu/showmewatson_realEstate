package com.watson.auth.admin.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RealtorLoginResponse {

    @NonNull
    private String accessToken;

    @NonNull
    private String refreshToken;

    @NonNull
    @Builder.Default
    private String role = "Realtor";

}
