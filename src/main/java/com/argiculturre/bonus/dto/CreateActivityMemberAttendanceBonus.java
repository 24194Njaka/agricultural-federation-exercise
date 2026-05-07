package com.argiculturre.bonus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateActivityMemberAttendanceBonus {
    private String memberIdentifier;
    private String attendanceStatus; // MISSING, ATTENDED, UNDEFINED
}
