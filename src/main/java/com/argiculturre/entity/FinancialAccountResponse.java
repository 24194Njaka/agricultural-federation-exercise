package com.argiculturre.entity;

import lombok.Data;

@Data
public class FinancialAccountResponse {
    private Long id;
    private String accountType;
    private String accountHolderName;
    private String bankName;
    private String mobileMoneyService;
    private String phoneNumber;
    private Double balanceAtDate;
    private String currency;

}
