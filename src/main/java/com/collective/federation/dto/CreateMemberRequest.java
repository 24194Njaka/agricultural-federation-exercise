package com.collective.federation.dto;

import com.collective.federation.entity.TypeGender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateMemberRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private TypeGender gender;
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private Long collectivityId;
    private List<SponsorInfo> sponsors;

    @Data
    public static class SponsorInfo {
        private Long sponsorId;
        private String relationship;
    }
}