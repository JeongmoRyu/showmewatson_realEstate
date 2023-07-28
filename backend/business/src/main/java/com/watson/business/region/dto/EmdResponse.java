package com.watson.business.region.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmdResponse {
    private String dongleeName;
    private String courtCode;

    public EmdResponse(String dongleeName, String courtCode) {
        this.dongleeName = dongleeName;
        this.courtCode = courtCode;
    }
}
