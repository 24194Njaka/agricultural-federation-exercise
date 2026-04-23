package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MembershipFeeResponse {
    private Long id;
    private String name;
    private Double amount;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}