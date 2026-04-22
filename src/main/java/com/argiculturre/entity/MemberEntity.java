package com.argiculturre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private TypeGender gender;
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private LocalDate membershipDate;
    private MemberRole role;
    private Long collectivityId;
    private String collectivityName;
}
