package com.argiculturre.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialAccountEntity {
    private String id;
    private String type;
    private BigDecimal amount;
}
