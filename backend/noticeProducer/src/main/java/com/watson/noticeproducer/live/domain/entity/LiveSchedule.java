package com.watson.noticeproducer.live.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "live_schedules")
public class LiveSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "realtor_id")
    private String realtorId;
    @Column(name = "house_id")
    private Long houseId;
    @Column(name = "live_date")
    private Date liveDate;
    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime regDate;
}
