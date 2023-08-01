package com.watson.auth.user.domain.entity;

import com.watson.auth.util.BaseEntity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Table(name="users")
public class User extends BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false) // oauth 때문에 임시로 추가
    private String role;

}