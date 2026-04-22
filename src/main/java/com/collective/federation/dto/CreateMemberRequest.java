package com.collective.federation.dto;

import com.collective.federation.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequest extends MemberEntity {

    private List<SponsorInfo> sponsors;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SponsorInfo {
        private Long sponsorId;
        private String relationship;
    }
}
