package com.watson.business.livenotice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class LiveAlarmResponse {
    private Long liveScheduleId;
    private Date liveDate;
    private String content;
}
