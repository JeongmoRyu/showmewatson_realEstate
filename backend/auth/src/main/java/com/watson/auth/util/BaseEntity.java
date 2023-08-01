package com.watson.auth.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;

/**
 * User, Realtor Table에 들어갈 공통 속성
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false)
    private Date regDate; // 생성일자

    @Column(nullable = false)
    private String authType; // 소셜 로그인 종류 (임시로 String. 원래는 enum)

    @Column(nullable = false)
    private String authId; // 소셜 로그인 ID

    @Column(nullable = false)
    private String password; // Security로 로그인 하기 위해 password 생성 (진짜 pw 아님)

    @Column(nullable = false)
    private boolean isBanned = false; //  정지 여부

    @Column(nullable = false)
    private boolean isDeleted = false; // 탈퇴 여부

    @Column(nullable = false)
    private String accessToken; // 로그인 액세스 토큰

    @Column(nullable = false)
    private String vapKey;

    @PrePersist
    protected void onCreate() {
        regDate = new Date(); // 현재 시간
    }

}
