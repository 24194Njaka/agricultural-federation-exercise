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
    private String number;
    private String name;
    private String location;
    private String specialisation;
    private LocalDate creationDate;
    private TypeStatus status;
    private LocalDate authorizationDate;
}