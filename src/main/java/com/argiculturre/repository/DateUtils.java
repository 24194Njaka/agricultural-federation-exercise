package com.argiculturre.repository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    /**
     * Calcule le nombre d'occurrences d'une cotisation sur une période donnée
     * @param startDate Date de début de la période
     * @param endDate Date de fin de la période
     * @param frequency Fréquence de la cotisation (WEEKLY, MONTHLY, ANNUALLY, PUNCTUALLY)
     * @param eligibleFrom Date à partir de laquelle la cotisation est éligible
     * @return Nombre d'occurrences
     */
    public static int getOccurrenceCount(LocalDate startDate, LocalDate endDate, String frequency, LocalDate eligibleFrom) {
        // La période commence au plus tard à la date d'éligibilité
        LocalDate effectiveStart = startDate;
        if (eligibleFrom != null && eligibleFrom.isAfter(startDate)) {
            effectiveStart = eligibleFrom;
        }

        // Si la date d'éligibilité est après la fin de la période, retourner 0
        if (eligibleFrom != null && eligibleFrom.isAfter(endDate)) {
            return 0;
        }

        switch (frequency.toUpperCase()) {
            case "WEEKLY":
                return (int) ChronoUnit.WEEKS.between(effectiveStart, endDate) + 1;
            case "MONTHLY":
                return (int) ChronoUnit.MONTHS.between(effectiveStart, endDate) + 1;
            case "ANNUALLY":
                return (int) ChronoUnit.YEARS.between(effectiveStart, endDate) + 1;
            case "PUNCTUALLY":
                return 1;
            default:
                return 1;
        }
    }
}