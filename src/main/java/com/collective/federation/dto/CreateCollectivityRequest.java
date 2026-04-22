package com.collective.federation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectivityRequest {
    private String number;
    private String name;
    private String specialty;
    private String city;
    private LocalDate creationDate;
    private List<MemberInfo> members;

    @Data
    @AllArgsConstructor
    public static class MemberInfo {
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
