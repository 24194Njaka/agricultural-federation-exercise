package com.collective.federation.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateCollectivityRequest {
    private String location;
    private List<String> members;
    private Boolean federationApproval;
    private CreateCollectivityStructure structure;

    @Data
    public static class CreateCollectivityStructure {
        private String president;
        private String vicePresident;
        private String treasurer;
        private String secretary;
    }
}