package com.watson.viewcalculator.util;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogHashCustomKey {
    private Long houseId;
    private String dongleeName;
    private LocalDate logDate;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogHashCustomKey logHashCustomKey = (LogHashCustomKey) o;
        return Objects.equals(houseId, logHashCustomKey.houseId) &&
                Objects.equals(dongleeName, logHashCustomKey.dongleeName) &&
                Objects.equals(logDate, logHashCustomKey.logDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(houseId, dongleeName, logDate);
    }
}
