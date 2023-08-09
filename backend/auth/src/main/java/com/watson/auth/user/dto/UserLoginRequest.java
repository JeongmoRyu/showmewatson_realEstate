package com.watson.auth.user.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserLoginRequest {

    @Id
    private String authId;

    @Column(nullable = false)
    private String authType;

}