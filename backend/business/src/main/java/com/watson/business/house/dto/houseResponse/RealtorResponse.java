package com.watson.business.house.dto.houseResponse;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealtorResponse {
    private String realtor_name;
    private String realtor_id;
}
