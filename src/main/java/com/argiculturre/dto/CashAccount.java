package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CashAccount implements FinancialAccount {
    private String id;
    private BigDecimal amount;
    private String type = "CASH";

    public CashAccount(String id, BigDecimal amount) {
    }
}
