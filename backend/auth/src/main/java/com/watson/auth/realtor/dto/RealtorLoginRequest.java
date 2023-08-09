package com.watson.auth.realtor.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RealtorLoginRequest {

    @Id
    private String authId;

    @Column(nullable = false)
    private String authType;

}