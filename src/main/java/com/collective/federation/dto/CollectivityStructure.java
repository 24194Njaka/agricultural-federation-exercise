package com.collective.federation.dto;

import lombok.Data;

@Data
public class CollectivityStructure {
    private MemberResponse president;
    private MemberResponse vicePresident;
    private MemberResponse treasurer;
    private MemberResponse secretary;
}