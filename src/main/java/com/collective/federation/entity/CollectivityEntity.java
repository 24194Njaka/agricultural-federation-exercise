package com.collective.federation.entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CollectivityEntity {
    private Long id;
    private String number;
    private String name;
    private String specialty;
    private String city;
    private LocalDate creationDate;
    private TypeStatus status;
    private LocalDate authorizationDate;
}
