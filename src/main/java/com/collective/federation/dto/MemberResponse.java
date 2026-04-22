package com.collective.federation.dto;

import lombok.Data;
import java.util.List;

@Data
public class MemberResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String address;
    private String profession;
    private Integer phoneNumber;
    private String email;
    private String occupation;
    private List<MemberResponse> referees;
}