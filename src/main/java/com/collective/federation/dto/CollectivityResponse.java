package com.collective.federation.dto;

import lombok.Data;
import java.util.List;

@Data
public class CollectivityResponse {
    private String id;
    private String location;
    private CollectivityStructure structure;
    private List<MemberResponse> members;
}