package com.watson.business.region.dto;

import lombok.*;

@Data
@Getter @Setter
@Builder
@AllArgsConstructor
public class EmdNameResponse {
    private String sidoName;
    private String gunguName;
    private String dongLeeName;
}
