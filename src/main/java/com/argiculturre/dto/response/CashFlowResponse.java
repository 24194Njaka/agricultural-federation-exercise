package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CashFlowResponse {
    private Long collectivityId;
    private String collectivityName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private List<TransactionResponse> transactions;
}