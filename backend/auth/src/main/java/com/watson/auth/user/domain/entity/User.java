package com.watson.auth.user.domain.entity;

import javax.persistence.*;

import com.watson.auth.util.BaseEntity;
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

}