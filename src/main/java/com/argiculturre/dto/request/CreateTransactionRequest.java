package com.argiculturre.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateTransactionRequest {
    private String accountId;
    private String memberId;
    private String transactionType;
    private Double amount;
    private String paymentMethod;
    private LocalDate transactionDate;
    private String description;
}