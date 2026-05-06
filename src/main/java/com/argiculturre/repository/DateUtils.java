package com.argiculturre.repository;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    /**
     * Calcule le nombre d'occurrences d'une cotisation sur une période donnée
     */
    public static int getOccurrenceCount(LocalDate startDate, LocalDate endDate, String frequency, LocalDate eligibleFrom) {
        // La cotisation n'est pas encore éligible
        if (eligibleFrom != null && endDate.isBefore(eligibleFrom)) {
            return 0;
        }

        LocalDate effectiveStart = eligibleFrom != null && eligibleFrom.isAfter(startDate) ? eligibleFrom : startDate;

        switch (frequency.toUpperCase()) {
            case "WEEKLY":
                return (int) java.time.Duration.between(effectiveStart.atStartOfDay(), endDate.atStartOfDay()).toDays() / 7 + 1;
            case "MONTHLY":
                return Period.between(effectiveStart, endDate).getMonths() + 1;
            case "ANNUALLY":
                return Period.between(effectiveStart, endDate).getYears() + 1;
            case "PUNCTUALLY":
                return 1;
            default:
                return 1;
        }
    }

    /**
     * Calcule le nombre de mois entre deux dates
     */
    public static int monthsBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end).getMonths() + (Period.between(start, end).getYears() * 12);
    }

    /**
     * Calcule le nombre d'années entre deux dates
     */
    public static int yearsBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end).getYears();
    }
}