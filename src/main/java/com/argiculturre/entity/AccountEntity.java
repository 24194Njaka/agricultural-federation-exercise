package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    private String id;
    private String entityType;
    private String entityId;
    private String accountType;
    private String accountName;
    private String accountHolderName;
    private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;
    private String ribKey;
    private String mobileMoneyService;
    private String phoneNumber;
    private Double balance;
    private String currency;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}