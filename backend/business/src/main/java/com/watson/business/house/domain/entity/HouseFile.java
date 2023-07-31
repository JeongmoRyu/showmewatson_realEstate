package com.watson.business.house.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house_files")
public class HouseFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
//    @JsonBackReference
    private House house;

    private String fileName;

    @CreatedDate
    private Date regDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void setHouse(House house) {
        this.house = house;
    }

    public HouseFile(String fileName) {
        this.fileName = fileName;
    }
}
