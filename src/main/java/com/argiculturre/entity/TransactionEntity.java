package com.argiculturre.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionEntity {
    private Long id;
    private Long accountId;
    private Long memberId;
    private String transactionType; // CONTRIBUTION, PAYMENT, REFUND
    private Double amount;
    private String paymentMethod; // CASH, BANK_TRANSFER, MOBILE_MONEY
    private LocalDate transactionDate;
    private String description;
    private LocalDate createdAt;
}