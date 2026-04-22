package com.collective.federation.controller;

import com.collective.federation.dto.CreateCollectivityRequest;
import com.collective.federation.dto.CollectivityResponse;
import com.collective.federation.service.CollectivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor
public class CollectivityController {

    private final CollectivityService collectivityService;

    @PostMapping
    public ResponseEntity<List<CollectivityResponse>> createCollectivity(@RequestBody List<CreateCollectivityRequest> requests) {
        List<CollectivityResponse> responses = collectivityService.createCollectivities(requests);
        return new ResponseEntity<>(responses, HttpStatus.CREATED);
    }
}