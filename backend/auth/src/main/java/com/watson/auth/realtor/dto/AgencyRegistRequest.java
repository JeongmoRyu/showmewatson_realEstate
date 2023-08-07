package com.watson.auth.realtor.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AgencyRegistRequest {

    @NonNull
    private String accessToken;

    @NonNull
    private String agencyName;

    @NonNull
    private String address;

    @NonNull
    private String tel;

    @NonNull
    private String agencyImg;

}
