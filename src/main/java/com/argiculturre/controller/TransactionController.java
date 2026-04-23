package com.argiculturre.controller;

import com.argiculturre.dto.request.CreateTransactionRequest;
import com.argiculturre.dto.response.CashFlowResponse;
import com.argiculturre.dto.response.TransactionResponse;
import com.argiculturre.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {
        TransactionResponse response = transactionService.createTransaction(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/cashflow/{collectivityId}")
    public ResponseEntity<CashFlowResponse> getCashFlow(
            @PathVariable String collectivityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        CashFlowResponse response = transactionService.getCashFlow(collectivityId, from, to);
        return ResponseEntity.ok(response);
    }
}