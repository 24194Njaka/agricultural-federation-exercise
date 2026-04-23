package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorshipEntity {
    private String id;
    private String memberId;
    private String sponsorId;
    private String relationship;
    private LocalDate sponsorshipDate;
}