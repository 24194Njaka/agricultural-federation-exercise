package com.argiculturre.dto;

import lombok.Data;

@Data
public class CreateMemberPayment {
    private Integer amount;
    private String membershipFeeIdentifier;
    private String accountCreditedIdentifier;
    private String paymentMode;
}
