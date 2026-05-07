package com.argiculturre.bonus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRecurrenceRuleBonus {
    private Integer weekOrdinal;  // 1-5
    private String dayOfWeek;     // MO, TU, WE, TH, FR, SA, SU
}