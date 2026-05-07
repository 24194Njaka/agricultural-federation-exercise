package com.argiculturre.bonus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ActivityMemberAttendanceBonus {

    private String id;
    private MemberDescriptionBonus memberDescription;
    private String attendanceStatus;

}
