package com.argiculturre.controller;

import com.argiculturre.dto.request.CreatePaymentRequest;
import com.argiculturre.dto.response.TransactionResponse;
import com.argiculturre.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{id}/payments")
    public ResponseEntity<List<TransactionResponse>> createPayments(
            @PathVariable String id,
            @RequestBody List<CreatePaymentRequest> requests) {
        List<TransactionResponse> responses = paymentService.createPayments(id, requests);
        return new ResponseEntity<>(responses, HttpStatus.CREATED);
    }
}