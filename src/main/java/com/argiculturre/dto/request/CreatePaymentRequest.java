package com.argiculturre.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreatePaymentRequest {
    private Long accountId;
    private Double amount;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String description;
}