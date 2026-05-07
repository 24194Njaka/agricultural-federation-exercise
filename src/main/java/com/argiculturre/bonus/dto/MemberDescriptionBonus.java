package com.argiculturre.bonus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDescriptionBonus {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String occupation;


}
