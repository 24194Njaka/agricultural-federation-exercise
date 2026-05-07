package com.argiculturre.bonus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectivityActivityBonus {
    private String label;
    private String activityType;  // MEETING, TRAINING, OTHER
    private List<String> memberOccupationConcerned; // JUNIOR, SENIOR, etc.
    private MonthlyRecurrenceRuleBonus recurrenceRule;   // optionnel
    private LocalDate executiveDate;                // optionnel
}
