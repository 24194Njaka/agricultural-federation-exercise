package com.argiculturre.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MembershipFeeEntity {
    private Long id;
    private Long collectivityId;
    private String name;
    private Double amount;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private LocalDate createdAt;
}