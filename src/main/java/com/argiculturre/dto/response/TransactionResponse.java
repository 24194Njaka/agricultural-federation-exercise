package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionResponse {
    private String id;
    private String accountId;
    private String memberId;
    private String memberName;
    private String transactionType;
    private Double amount;
    private String paymentMethod;
    private LocalDate transactionDate;
    private String description;
}