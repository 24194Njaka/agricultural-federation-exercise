package com.argiculturre.dto.response;

import lombok.Data;

@Data
public class FinancialAccountResponse {
    private String id;
    private String accountType;
    private String accountName;
    private String accountHolderName;
    private String bankName;
    private String mobileMoneyService;
    private String phoneNumber;
    private Double balanceAtDate;
    private String currency;
}