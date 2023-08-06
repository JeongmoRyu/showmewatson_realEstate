package com.watson.business.liveschedule.service;

import com.watson.business.liveschedule.domain.entity.LiveSchedule;
import com.watson.business.liveschedule.domain.repository.LiveScheduleRepository;
import com.watson.business.liveschedule.dto.LiveScheduleRequest;
import com.watson.business.liveschedule.dto.LiveScheduleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LiveScheduleServiceImp implements LiveScheduleService {
    private final LiveScheduleRepository liveScheduleRepository;

    @Override
    public List<LiveScheduleResponse> findAllLiveSchedules() {
        List<LiveSchedule> liveSchedules = liveScheduleRepository.findAll();
        List<LiveScheduleResponse> responses = new ArrayList<>();
        for(LiveSchedule liveSchedule : liveSchedules) {
            LiveScheduleResponse response = liveScheduleEntityToDto(liveSchedule);
            responses.add(response);
        }
        return responses;
    }
    @Override
    public LiveScheduleResponse findLiveScheduleByScheduleId(Long id) {
        LiveSchedule liveSchedule = liveScheduleRepository.findLiveScheduleById(id);
        return liveScheduleEntityToDto(liveSchedule);
    }
    @Override
    public Long addLiveSchedule(LiveScheduleRequest request) {
        LiveSchedule liveSchedule = liveScheduleRepository.save(LiveSchedule.builder()
                        .realtorId(request.getRealtorId())
                        .houseId(request.getHouseId())
                        .liveDate(request.getLiveDate())
                        .content(request.getContent())
                        .build());
        return liveSchedule.getId();
    }
    private LiveScheduleResponse liveScheduleEntityToDto(LiveSchedule entity) {
        return LiveScheduleResponse.builder()
                .id(entity.getId())
                .realtorId(entity.getRealtorId())
                .houseId(entity.getHouseId())
                .liveDate(entity.getLiveDate())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .build();
    }
}
