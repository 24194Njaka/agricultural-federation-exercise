package com.argiculturre.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateMember extends MemberInformation {
    private String collectivityIdentifier;
    private List<String> referees;
    private Boolean registrationFeePaid;
    private Boolean membershipDuesPaid;

}