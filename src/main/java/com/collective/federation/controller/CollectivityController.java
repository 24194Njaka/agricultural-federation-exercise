package com.collective.federation.controller;

import com.collective.federation.entity.CollectivityEntity;
import com.collective.federation.service.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {
    private final CollectivityService collectivityService;

    public CollectivityController(CollectivityService collectivityService) {
        this.collectivityService = collectivityService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CollectivityEntity collectivity) {
        try {
            collectivityService.createCollectivity(collectivity);
            return ResponseEntity.ok("Collectivity created successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
