package com.watson.business.livenotice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NoticeUser {
    private String fcmToken;
    private String liveSchedulesId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeUser that = (NoticeUser) o;
        return Objects.equals(fcmToken, that.fcmToken) &&
                Objects.equals(liveSchedulesId, that.liveSchedulesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fcmToken, liveSchedulesId);
    }
}
