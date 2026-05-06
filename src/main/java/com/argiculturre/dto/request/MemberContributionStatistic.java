package com.argiculturre.dto.request;

import lombok.Data;

@Data
public class MemberContributionStatistic {
    private String memberId;
    private String memberName;
    private Double totalContribution;
    private Double pendingAmount;
}
