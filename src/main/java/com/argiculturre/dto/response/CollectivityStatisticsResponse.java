package com.argiculturre.dto.response;

import com.argiculturre.dto.request.MemberContributionStatistic;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CollectivityStatisticsResponse {
    private String collectivityId;
    private String collectivityName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<MemberContributionStatistic> memberContributionStatistics;
}
