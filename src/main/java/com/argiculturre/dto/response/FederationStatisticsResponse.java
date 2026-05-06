package com.argiculturre.dto.response;

import lombok.Data;

@Data
public class FederationStatisticsResponse {
    private String collectivityId;
    private String collectivityName;
    private Double contributionCompilianceRate;
    private Integer newMembersCount;
}
