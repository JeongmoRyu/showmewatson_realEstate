package com.watson.auth.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserUpdateRequest {

    @NonNull
    private String nickname;

    @NonNull
    private String accessToken;

}
