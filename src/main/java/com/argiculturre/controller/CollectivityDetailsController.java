package com.argiculturre.controller;

import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.dto.response.TransactionResponse;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.entity.FinancialAccountResponse;
import com.argiculturre.service.CollectivityService;
import com.argiculturre.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/collectivities")
@RequiredArgsConstructor
public class CollectivityDetailsController {

    private final CollectivityService collectivityService;
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<CollectivityResponse> getCollectivityById(@PathVariable Long id) {
        CollectivityEntity collectivity = collectivityService.getCollectivityById(id);
        CollectivityResponse response = collectivityService.mapToResponse(collectivity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccountResponse>> getFinancialAccounts(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        List<FinancialAccountResponse> accounts = collectivityService.getFinancialAccounts(id, at);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getCollectivityTransactions(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<TransactionResponse> transactions = transactionService.getCollectivityTransactions(id, from, to);
        return ResponseEntity.ok(transactions);
    }
}