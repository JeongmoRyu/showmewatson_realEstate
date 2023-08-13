package com.watson.business.liveschedule.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveScheduleResponse {
    private Long id;
    private String realtorId;
    private Long houseId;
    private String liveDate;
    private String content;
    private LocalDateTime regDate;
}
