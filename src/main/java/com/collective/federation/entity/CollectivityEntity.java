package com.collective.federation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
