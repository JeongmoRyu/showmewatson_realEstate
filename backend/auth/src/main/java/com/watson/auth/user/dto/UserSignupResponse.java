package com.watson.auth.user.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.watson.auth.util.AuthType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserSignupResponse {

    @NonNull
    private String authId;

    @Enumerated(EnumType.STRING)
    @NonNull
    private AuthType authType;

}