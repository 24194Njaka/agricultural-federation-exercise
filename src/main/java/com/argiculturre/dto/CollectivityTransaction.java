package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CollectivityTransaction {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    private BigDecimal amount;
    private String paymentMode; // CASH, MOBILE_BANKING, BANK_TRANSFER
    private FinancialAccount accountCredited;
    private Member memberDebited;

}
