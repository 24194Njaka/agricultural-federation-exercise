package com.argiculturre.controller;

import com.argiculturre.dto.request.CreateCollectivityRequest;
import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.service.CollectivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}