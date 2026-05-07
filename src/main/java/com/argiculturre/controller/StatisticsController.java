package com.argiculturre.controller;

import com.argiculturre.dto.CollectivityStatisticsResponse;
import com.argiculturre.dto.FederationStatisticsResponse;
import com.argiculturre.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/{id}/statistics")
    public ResponseEntity<CollectivityStatisticsResponse> getCollectivityStatistics(
            @PathVariable("id") String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        CollectivityStatisticsResponse response = statisticsService.getCollectivityStatistics(id, from, to);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<FederationStatisticsResponse> getFederationStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        FederationStatisticsResponse response = statisticsService.getFederationStatistics(from, to);
        return ResponseEntity.ok(response);
    }
}