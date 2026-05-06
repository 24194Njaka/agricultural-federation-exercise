package com.argiculturre.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReferenceEntity {
    private String candidateId;
    private String sponsorId;
    private String relationNature;
    private LocalDate sponsorshipDate;
}
