package com.watson.auth.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RealtorSignupResponse {

    @NonNull
    private String authId;

    @NonNull
    private String authType;

}
