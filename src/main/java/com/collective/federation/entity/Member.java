package com.collective.federation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender; // MALE or FEMALE [cite: 15]
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private String occupation; // PRESIDENT, JUNIOR, etc. [cite: 16]
    private String collectivityId; // Foreign key to Collectivity
    private boolean registrationFeePaid; // 50,000 MGA [cite: 33, 89]
    private boolean membershipDuesPaid;  // Annual dues [cite: 89]
    private LocalDate joiningDate;
}
