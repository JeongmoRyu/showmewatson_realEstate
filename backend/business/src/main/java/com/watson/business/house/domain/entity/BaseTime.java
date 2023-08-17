package com.watson.business.house.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {
    @CreatedDate
	@Column(name="reg_date", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
	@Column(name="edit_date")
    private LocalDateTime editDate;
}
