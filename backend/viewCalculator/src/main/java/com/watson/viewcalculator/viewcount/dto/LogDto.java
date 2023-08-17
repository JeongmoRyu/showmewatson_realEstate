package com.watson.viewcalculator.viewcount.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class LogDto {
    private String userId;
    private Long houseId;
    private LocalDate logDate;
    private String dongleeName;

    public LogDto(String userId, Long houseId, LocalDate logDate, String dongleeName) {
        this.userId = userId;
        this.houseId = houseId;
        this.logDate = logDate;
        this.dongleeName = dongleeName;
    }
}
