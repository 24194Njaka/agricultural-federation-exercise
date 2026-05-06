package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MembershipFee {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eligibleFrom;
    private String frequency;
    private BigDecimal amount;
    private String label;
    private String status;
}
