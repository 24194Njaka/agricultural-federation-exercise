package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipFeeEntity {
    private String id;
    private String collectivityId;
    private String label;
    private Double amount;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private LocalDate createdAt;
}