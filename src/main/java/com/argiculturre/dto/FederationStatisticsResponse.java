package com.argiculturre.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class FederationStatisticsResponse {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CollectivityGlobalStatistic> collectivityStatistics;
}