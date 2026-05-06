package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberPayment {
    private String id;
    private Integer amount;
    private String paymentMode;
    private FinancialAccount accountCredited;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
}
