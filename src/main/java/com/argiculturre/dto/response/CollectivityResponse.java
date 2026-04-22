package com.argiculturre.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CollectivityResponse {
    private Long id;
    private String number;
    private String name;
    private String location;
    private LocalDate creationDate;
    private String status;
    private Integer memberCount;
    private List<MemberResponse> members;
}
