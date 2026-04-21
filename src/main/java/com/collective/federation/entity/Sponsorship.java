package com.collective.federation.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Sponsorship {
    private Long id;
    private Long memberId;
    private Long sponsorId;
    private String relationship;
    private LocalDate sponsorshipDate;
}
