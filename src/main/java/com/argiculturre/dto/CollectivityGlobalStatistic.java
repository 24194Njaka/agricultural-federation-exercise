package com.argiculturre.dto;

import lombok.Data;

@Data
public class CollectivityGlobalStatistic {
    private String  collectivityId;
    private String  collectivityName;
    private Double contributionComplianceRate;
    private Integer newMembersCount;
}
