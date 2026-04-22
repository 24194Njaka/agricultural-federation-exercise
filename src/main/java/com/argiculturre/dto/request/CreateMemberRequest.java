package com.argiculturre.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateMemberRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String profession;
    private String phone;
    private String email;
    private Long collectivityId;
    private List<SponsorInfo> sponsors;

    @Data
    public static class SponsorInfo {
        private Long sponsorId;
        private String relationship;
    }

}