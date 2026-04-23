package com.argiculturre.controller;

import com.argiculturre.dto.request.AssignIdentityRequest;
import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @PutMapping("/{id}/identity")
    public ResponseEntity<CollectivityResponse> assignIdentity(
            @PathVariable Long id,
            @RequestBody AssignIdentityRequest request) {
        CollectivityResponse response = identityService.assignIdentity(id, request);
        return ResponseEntity.ok(response);
    }
}