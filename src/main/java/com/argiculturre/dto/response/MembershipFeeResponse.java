package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MembershipFeeResponse {
    private String id;
    private String label;
    private Double amount;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}