package com.argiculturre.controller;

import com.argiculturre.dto.FinancialAccount;
import com.argiculturre.service.FinancialAccountService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CollectivityFinancialAccountController {

    private final FinancialAccountService financialAccountService;

    public CollectivityFinancialAccountController(FinancialAccountService financialAccountService) {
        this.financialAccountService = financialAccountService;
    }

    @GetMapping("/collectivities/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccount>> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        List<FinancialAccount> accounts = financialAccountService.getFinancialAccounts(id, at);
        return ResponseEntity.ok(accounts);
    }
}
