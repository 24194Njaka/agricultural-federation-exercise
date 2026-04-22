package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AccountResponse {
    private Long id;
    private String entityType;
    private Long entityId;
    private String accountType;
    private String accountName;
    private String accountHolderName;
    private String bankName;
    private String mobileMoneyService;
    private String phoneNumber;
    private Double balance;
    private String currency;
}