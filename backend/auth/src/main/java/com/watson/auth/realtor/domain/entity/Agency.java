package com.watson.auth.realtor.domain.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "agencies")
public class Agency {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String registId;

    @NonNull
    private String agencyName;

    @NonNull
    private String address;

    @NonNull
    private String tel;

    @NonNull
    private String agencyImg;

}

