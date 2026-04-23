package com.argiculturre.controller;

import com.argiculturre.dto.request.CreateMembershipFeeRequest;
import com.argiculturre.dto.response.MembershipFeeResponse;
import com.argiculturre.service.MembershipFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor
public class MembershipFeeController {

    private final MembershipFeeService membershipFeeService;

    @PostMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> createMembershipFees(
            @PathVariable Long id,
            @RequestBody List<CreateMembershipFeeRequest> requests) {
        List<MembershipFeeResponse> responses = membershipFeeService.createMembershipFees(id, requests);
        return new ResponseEntity<>(responses, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> getMembershipFees(@PathVariable Long id) {
        List<MembershipFeeResponse> responses = membershipFeeService.getMembershipFees(id);
        return ResponseEntity.ok(responses);
    }
}