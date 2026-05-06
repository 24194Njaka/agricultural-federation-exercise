package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CashAccount implements FinancialAccount {
    private String id;
    private BigDecimal amount;
    private String type = "CASH";

    public CashAccount(String id, BigDecimal amount) {
    }
}
