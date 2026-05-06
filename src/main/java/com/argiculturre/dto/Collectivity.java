package com.argiculturre.dto;

import lombok.Data;

import java.util.List;

@Data
public class Collectivity {
    private String id;
    private String name;
    private Integer number;
    private String location;
    private CollectivityStructure structure;
    private List<Member> members;
}