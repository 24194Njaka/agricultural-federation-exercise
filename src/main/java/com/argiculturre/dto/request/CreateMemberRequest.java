package com.argiculturre.dto.request;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateMemberRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String profession;
    private String phone;
    private String email;
    private String collectivityId;
    private List<SponsorInfo> sponsors;

    @Data
    public static class SponsorInfo {
        private String sponsorId;
        private String relationship;
    }
}