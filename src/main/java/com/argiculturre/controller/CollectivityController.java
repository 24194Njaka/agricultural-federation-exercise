package com.argiculturre.controller;

import com.argiculturre.dto.Collectivity;
import com.argiculturre.dto.CollectivityInformation;
import com.argiculturre.dto.CreateCollectivity;
import com.argiculturre.service.CollectivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CollectivityController {

    private final CollectivityService collectivityService;

    public CollectivityController(CollectivityService collectivityService) {
        this.collectivityService = collectivityService;
    }

    @PostMapping("/collectivities")
    public ResponseEntity<List<Collectivity>> createCollectivities(@RequestBody List<CreateCollectivity> createList) {
        List<Collectivity> result = collectivityService.createCollectivities(createList);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/collectivities/{id}/informations")
    public ResponseEntity<Collectivity> updateCollectivityInformation(
            @PathVariable String id,
            @RequestBody CollectivityInformation info) {
        Collectivity updated = collectivityService.updateCollectivityInformation(id, info);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/collectivities/{id}")
    public ResponseEntity<Collectivity> getCollectivityById(@PathVariable String id) {
        Collectivity collectivity = collectivityService.getCollectivityById(id);
        return ResponseEntity.ok(collectivity);
    }
}