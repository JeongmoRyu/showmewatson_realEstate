package com.watson.business.liveschedule.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveScheduleResponse {
    private Long id;
    private String realtorId;
    private Long houseId;
    private Date liveDate;
    private String content;
    private LocalDateTime regDate;
}
