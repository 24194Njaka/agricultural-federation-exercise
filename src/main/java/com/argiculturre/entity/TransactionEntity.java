package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    private String id;
    private String accountId;
    private String memberId;
    private String transactionType;
    private Double amount;
    private String paymentMethod;
    private LocalDate transactionDate;
    private String description;
    private LocalDate createdAt;
}