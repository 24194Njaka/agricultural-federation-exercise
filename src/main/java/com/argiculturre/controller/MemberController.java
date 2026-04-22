package com.argiculturre.controller;


import com.argiculturre.dto.request.CreateMemberRequest;
import com.argiculturre.dto.response.MemberResponse;
import com.argiculturre.service.MemberService;
import com.argiculturre.service.impl.MemberServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService service = new MemberServiceImpl();
    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody CreateMemberRequest request) {
        return ResponseEntity.status(201).body(service.createMember(request));
    }
}