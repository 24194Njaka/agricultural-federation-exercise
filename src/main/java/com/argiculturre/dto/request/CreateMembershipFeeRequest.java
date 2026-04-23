package com.argiculturre.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateMembershipFeeRequest {
    private String label;
    private Double amount;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}