package com.argiculturre.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CollectivityResponse {
    private String id;
    private String number;
    private String name;
    private String location;
    private String specialisation;
    private LocalDate creationDate;
    private String status;
    private Integer memberCount;
    private List<MemberResponse> members;
}