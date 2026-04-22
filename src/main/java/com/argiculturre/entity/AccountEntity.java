package com.argiculturre.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AccountEntity {
    private Long id;
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
    private String mobileMoneyService; // ORANGE_MONEY, MVOLA, AIRTEL_MONEY
    private String phoneNumber;
    private Double balance;
    private String currency; // MGA
    private LocalDate createdAt;
    private LocalDate updatedAt;
}