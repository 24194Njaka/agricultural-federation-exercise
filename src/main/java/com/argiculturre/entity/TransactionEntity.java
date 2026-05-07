package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    private String id;
    private String memberId;
    private String collectivityId;
    private BigDecimal amount;
    private String paymentMode;
    private String accountCreditedId;
    private String membershipFeeId;
    private LocalDate creationDate;
}