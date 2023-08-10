package com.watson.business.livenotice.server;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.livenotice.domain.entity.NoticeUser;
import com.watson.business.livenotice.domain.entity.Notification;
import com.watson.business.livenotice.domain.repository.NotificationRepository;
import com.watson.business.livenotice.dto.LiveAlarmResponse;
import com.watson.business.liveschedule.domain.entity.LiveSchedule;
import com.watson.business.liveschedule.domain.repository.LiveScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LiveNoticeService {

    private final LiveScheduleRepository liveScheduleRepository;
    private final NotificationRepository notificationRepository;
    public void registLiveNotice(String liveSchedulesId, String fcmToken) {
        try {
            Date liveDate = liveScheduleRepository.findLiveDateById(Long.valueOf(liveSchedulesId));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            String formattedDate = sdf.format(liveDate);

            log.info("liveDate: {}", formattedDate);

            Optional<Notification> existingLiveSchedule = notificationRepository.findById(formattedDate);

            NoticeUser newUser = NoticeUser.builder()
                    .fcmToken(fcmToken)
                    .liveSchedulesId(liveSchedulesId).build();
            if (existingLiveSchedule.isPresent()) {
                Set<NoticeUser> users = existingLiveSchedule.get().getUsers();

                if (!users.contains(newUser)) {
                    users.add(newUser);
                    existingLiveSchedule.get().setUsers(users);
                    notificationRepository.save(existingLiveSchedule.get());
                }
            } else {
                Set<NoticeUser> users = new HashSet<>();
                users.add(newUser);
                Notification newNotification = Notification.builder()
                        .liveDate(formattedDate)
                        .users(users)
                        .build();
                notificationRepository.save(newNotification);
            }
        } catch (NullPointerException e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_LIVE_INFO);
        }
    }
    public void cancelLiveNotice(String liveSchedulesId, String fcmToken) {
        try {
            Date liveDate = liveScheduleRepository.findLiveDateById(Long.valueOf(liveSchedulesId));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            String formattedDate = sdf.format(liveDate);

            Optional<Notification> existingLiveSchedule = notificationRepository.findById(formattedDate);
            if (existingLiveSchedule.isPresent()) {
                Set<NoticeUser> users = existingLiveSchedule.get().getUsers();

                // 찾아서 삭제할 NoticeUser 객체 생성
                NoticeUser userToDelete = NoticeUser.builder()
                        .fcmToken(fcmToken)
                        .liveSchedulesId(liveSchedulesId)
                        .build();

                // Set에서 해당 NoticeUser 삭제
                users.remove(userToDelete);

                // 변경된 Set 저장
                existingLiveSchedule.get().setUsers(users);
                notificationRepository.save(existingLiveSchedule.get());
            }
        } catch (NullPointerException e) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_LIVE_INFO);
        }
    }
    public List<LiveAlarmResponse> getLiveNoticeList(String fcmToken) {
        List<String> liveSchedules = new ArrayList<>();

        List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            List<String> matchingScheduleId = notification.getUsers().stream()
                    .filter(user -> user.getFcmToken().equals(fcmToken))
                    .map(NoticeUser::getLiveSchedulesId)
                    .collect(Collectors.toList());
            liveSchedules.addAll(matchingScheduleId);
        }

        // 해당 liveSchedulesId로 HouseResponse, liveStartDate, liveContent 가져오기
        List<LiveAlarmResponse> notificationResponses = new ArrayList<>();
        for(String scheduleId: liveSchedules) {
            LiveSchedule liveSchedule = liveScheduleRepository.findLiveScheduleById(Long.valueOf(scheduleId));

            notificationResponses.add(LiveAlarmResponse.builder()
                    .liveScheduleId(liveSchedule.getId())
                    .liveDate(liveSchedule.getLiveDate())
                    .content(liveSchedule.getContent()).build());
        }

        return notificationResponses;
    }
}
