package com.watson.business.livenotice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class LiveAlarmResponse {
    private Long liveScheduleId;
    private String liveDate;
    private String content;
}
