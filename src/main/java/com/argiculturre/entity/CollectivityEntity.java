package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectivityEntity {
    private String id;
    private String location;
    private String specialiteAgricole;
    private Integer annualDuesAmount;
    private LocalDate dateCreation;
    private Boolean federationApproval;
    private String name;
    private Integer number;
}