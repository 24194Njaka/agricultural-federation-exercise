package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SponsorshipEntity {
    private Long id;
    private Long memberId;
    private Long sponsorId;
    private String relationship;
    private LocalDate sponsorshipDate;
}
