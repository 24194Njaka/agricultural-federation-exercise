package com.argiculturre.controller;

import com.argiculturre.dto.request.CreateMemberRequest;
import com.argiculturre.dto.response.MemberResponse;
import com.argiculturre.entity.MemberEntity;
import com.argiculturre.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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

     @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberEntity> members = memberService.getAllMembers();
        List<MemberResponse> responses = members.stream()
                .map(memberService::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

     @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable String id) {
        MemberEntity member = memberService.getMemberById(id);
        MemberResponse response = memberService.mapToResponse(member);
        return ResponseEntity.ok(response);
    }
}