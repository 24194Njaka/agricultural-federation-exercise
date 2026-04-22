package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionResponse {
    private Long id;
    private Long accountId;
    private Long memberId;
    private String memberName;
    private String transactionType;
    private Double amount;
    private String paymentMethod;
    private LocalDate transactionDate;
    private String description;
}