package com.collective.federation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collectivity {
    private String id;
    private String name;
    private String location;
    private String specialty;
    private LocalDate creationDate;
    private boolean federationApproval;
}
