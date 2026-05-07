package com.argiculturre.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRecurrenceRule {
    private Integer weekOrdinal;  // 1-5
    private String dayOfWeek;     // MO, TU, WE, TH, FR, SA, SU
}