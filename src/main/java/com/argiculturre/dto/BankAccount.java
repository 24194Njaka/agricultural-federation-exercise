package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount implements FinancialAccount {
    private String id;
    private String holderName;
    private String bankName;
    private Integer bankCode;
    private Integer bankBranchCode;
    private Integer bankAccountNumber;
    private Integer bankAccountKey;
    private BigDecimal amount;
    private String type = "BANK";

}