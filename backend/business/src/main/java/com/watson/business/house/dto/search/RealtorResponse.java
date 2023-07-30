package com.watson.business.house.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RealtorResponse {
    private String realtor_name;
    private String realtor_id;

    public RealtorResponse(String realtor_name, String realtor_id) {
        this.realtor_name = realtor_name;
        this.realtor_id = realtor_id;
    }
}
