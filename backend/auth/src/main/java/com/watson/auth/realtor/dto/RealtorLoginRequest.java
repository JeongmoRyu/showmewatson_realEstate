package com.watson.auth.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RealtorLoginRequest {

    private String authId;
    private String authType;

}