package com.argiculturre.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateCollectivity {
    private String location;
    private List<String> members;
    private Boolean federationApproval;
    private CreateCollectivityStructure structure;

}