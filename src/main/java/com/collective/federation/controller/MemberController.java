package com.collective.federation.controller;

import com.collective.federation.dto.CreateMemberRequest;
import com.collective.federation.dto.MemberResponse;
import com.collective.federation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody CreateMemberRequest request) {
        MemberResponse response = memberService.createMember(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}