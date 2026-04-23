package com.argiculturre.controller;

import com.argiculturre.dto.request.AssignIdentityRequest;
import com.argiculturre.dto.request.CreateCollectivityRequest;
import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.service.CollectivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor
public class CollectivityController {

    private final CollectivityService collectivityService;

    @PostMapping
    public ResponseEntity<CollectivityResponse> createCollectivity(@RequestBody CreateCollectivityRequest request) {
        CollectivityResponse response = collectivityService.createCollectivity(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CollectivityResponse>> getAllCollectivities() {
        List<CollectivityEntity> collectivities = collectivityService.getAllCollectivities();
        List<CollectivityResponse> responses = collectivities.stream()
                .map(collectivityService::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectivityResponse> getCollectivityById(@PathVariable String id) {
        CollectivityEntity collectivity = collectivityService.getCollectivityById(id);
        CollectivityResponse response = collectivityService.mapToResponse(collectivity);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/informations")
    public ResponseEntity<CollectivityResponse> assignIdentity(
            @PathVariable String id,
            @RequestBody AssignIdentityRequest request) {
        CollectivityResponse response = collectivityService.assignIdentity(id, request);
        return ResponseEntity.ok(response);
    }
}