package com.argiculturre.controller;

import com.argiculturre.dto.response.TransactionResponse;
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

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getCollectivityTransactions(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<TransactionResponse> responses = transactionService.getCollectivityTransactions(id, from, to);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccountResponse>> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        List<com.argiculturre.entity.FinancialAccountResponse> responses = collectivityService.getFinancialAccounts(id, at);
        return ResponseEntity.ok(responses);
    }
}