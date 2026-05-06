package com.argiculturre.dto;

import lombok.Data;

import java.util.List;

@Data
public class Member extends MemberInformation {
    private String id;
    private List<Member> referees;

}