package com.argiculturre.dto.request;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String entityType; // COLLECTIVITY, FEDERATION
    private Long entityId;
    private String accountType; // CASH, BANK, MOBILE_MONEY
    private String accountName;
    private String accountHolderName;

     private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String ribKey;

     private String mobileMoneyService;
    private String phoneNumber;
}