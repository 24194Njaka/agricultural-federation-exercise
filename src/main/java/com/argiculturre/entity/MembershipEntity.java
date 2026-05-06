package com.argiculturre.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MembershipEntity {
    private String memberId;
    private String collectivityId;
    private String occupation;
    private Boolean registrationFeePaid;
    private Boolean membershipDuesPaid;
    private LocalDate dateAdhesion;
    private LocalDate paymentDate;
}
