package com.watson.business.house.dto.houseresponse;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealtorResponse {
    private String realtorName;
    private String realtorId;
}
