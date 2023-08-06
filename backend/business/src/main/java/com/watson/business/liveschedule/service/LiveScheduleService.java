package com.watson.business.liveschedule.service;

import com.watson.business.liveschedule.dto.LiveScheduleRequest;
import com.watson.business.liveschedule.dto.LiveScheduleResponse;

import java.util.List;

public interface LiveScheduleService {
    List<LiveScheduleResponse> findAllLiveSchedules();
    LiveScheduleResponse findLiveScheduleByScheduleId(Long id);
    Long addLiveSchedule(LiveScheduleRequest request);
}
