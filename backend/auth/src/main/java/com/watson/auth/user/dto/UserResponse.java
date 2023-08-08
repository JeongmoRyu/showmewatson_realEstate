package com.watson.auth.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserResponse {

    @NonNull
    private String nickname;

}
