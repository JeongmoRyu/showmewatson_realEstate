package com.watson.viewcalculator.viewcount.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HashMapCustomValue {
    private LocalDate logDate;
    private String dongleeName;
    private Long viewCount;
    public void incrementViewCount(){
        this.viewCount++;
    }
}
