package com.collective.federation.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String profession;
    private String phone;
    private String email;
    private LocalDate membershipDate;
    private MemberRole role;
    private Long collectivityId;
}
