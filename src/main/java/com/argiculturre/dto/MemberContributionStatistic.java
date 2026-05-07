package com.argiculturre.dto;

import lombok.Data;

@Data
public class MemberContributionStatistic {
    private String memberId;
    private String memberName;
    private Double totalContribution;
    private Double pendingAmount;
}
