package com.argiculturre.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateCollectivityRequest {
    private String location;
    private LocalDate creationDate;
    private List<MemberInfo> members;

    @Data
    public static class MemberInfo {
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
        private String role;
    }
}