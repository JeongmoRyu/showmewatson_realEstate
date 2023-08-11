package com.watson.noticeproducer.live.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document("notification")
@Builder
@AllArgsConstructor
public class NotificationInfo {

    @Id
    private String liveDate;
    private Set<NoticeUser> users;

}
