package com.argiculturre.controller;

import com.argiculturre.dto.request.AssignCollectivityIdentityRequest;
import com.argiculturre.dto.request.CreateCollectivityRequest;
import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.service.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService collectivityService;

    public CollectivityController(CollectivityService collectivityService) {
        this.collectivityService = collectivityService;
    }

    @PostMapping
    public ResponseEntity<CollectivityResponse> create(@RequestBody CreateCollectivityRequest request) {
        return ResponseEntity.ok(collectivityService.createCollectivity(request));
    }

    @PatchMapping("/identity")
    public ResponseEntity<CollectivityResponse> assignIdentity(@RequestBody AssignCollectivityIdentityRequest request) {
        return ResponseEntity.ok(collectivityService.assignIdentity(request));
    }

    @GetMapping
    public ResponseEntity<List<CollectivityResponse>> findAll() {
        return ResponseEntity.ok(collectivityService.findAll());
    }
}
