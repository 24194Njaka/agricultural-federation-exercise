package com.collective.federation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Referee {
    private String candidateId;
    private String refereeId;
    private String relationship; // e.g., family, friend, colleague
}
