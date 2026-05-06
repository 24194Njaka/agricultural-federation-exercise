package com.argiculturre.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileBankingAccount implements FinancialAccount {
    private String id;
    private String holderName;
    private String mobileBankingService;
    private String mobileNumber;
    private BigDecimal amount;
    private String type = "MOBILE_BANKING";
}