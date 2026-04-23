package com.argiculturre.dto.response;

import lombok.Data;

@Data
public class AccountResponse {
    private String id;
    private String entityType;
    private String entityId;
    private String accountType;
    private String accountName;
    private String accountHolderName;
    private String bankName;
    private String mobileMoneyService;
    private String phoneNumber;
    private Double balance;
    private String currency;
}