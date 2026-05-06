package com.argiculturre.controller;

import com.argiculturre.dto.CreateMembershipFee;
import com.argiculturre.dto.MembershipFee;
import com.argiculturre.service.MembershipFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CollectivityMembershipFeeController {

    private final MembershipFeeService membershipFeeService;

    public CollectivityMembershipFeeController(MembershipFeeService membershipFeeService) {
        this.membershipFeeService = membershipFeeService;
    }

    @GetMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<List<MembershipFee>> getMembershipFees(@PathVariable String id) {
        List<MembershipFee> fees = membershipFeeService.getMembershipFees(id);
        return ResponseEntity.ok(fees);
    }

    @PostMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<List<MembershipFee>> createMembershipFees(
            @PathVariable String id,
            @RequestBody List<CreateMembershipFee> createFees) {
        List<MembershipFee> createdFees = membershipFeeService.createMembershipFees(id, createFees);
        return ResponseEntity.ok(createdFees); // Selon la spec, retour 200, pas 201
    }
}