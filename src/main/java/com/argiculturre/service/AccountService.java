package com.argiculturre.service;

import com.argiculturre.dto.request.CreateAccountRequest;
import com.argiculturre.dto.response.AccountResponse;
import com.argiculturre.entity.AccountEntity;
import com.argiculturre.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        // Vérifier qu'une collectivité ne peut avoir qu'une seule caisse
        if ("CASH".equals(request.getAccountType())) {
            if (accountRepository.hasCashAccount(request.getEntityType(), request.getEntityId())) {
                throw new RuntimeException("This entity already has a CASH account");
            }
        }

        // Valider le format du numéro de compte bancaire (23 chiffres)
        if ("BANK".equals(request.getAccountType())) {
            validateBankAccount(request);
        }

        // Valider le compte mobile money
        if ("MOBILE_MONEY".equals(request.getAccountType())) {
            validateMobileMoney(request);
        }

        AccountEntity account = new AccountEntity();
        account.setEntityType(request.getEntityType());
        account.setEntityId(request.getEntityId());
        account.setAccountType(request.getAccountType());
        account.setAccountName(request.getAccountName());
        account.setAccountHolderName(request.getAccountHolderName());
        account.setBankName(request.getBankName());
        account.setBankCode(request.getBankCode());
        account.setBranchCode(request.getBranchCode());
        account.setAccountNumber(request.getAccountNumber());
        account.setRibKey(request.getRibKey());
        account.setMobileMoneyService(request.getMobileMoneyService());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setBalance(0.0);
        account.setCurrency("MGA");

        AccountEntity saved = accountRepository.save(account);
        return mapToResponse(saved);
    }

    private void validateBankAccount(CreateAccountRequest request) {
        // Vérifier que le numéro de compte a 23 chiffres
        String fullNumber = (request.getBankCode() != null ? request.getBankCode() : "") +
                (request.getBranchCode() != null ? request.getBranchCode() : "") +
                (request.getAccountNumber() != null ? request.getAccountNumber() : "") +
                (request.getRibKey() != null ? request.getRibKey() : "");

        if (fullNumber.length() != 23) {
            throw new RuntimeException("Bank account number must have 23 digits");
        }
    }

    private void validateMobileMoney(CreateAccountRequest request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            throw new RuntimeException("Phone number is required for mobile money account");
        }
    }

    private AccountResponse mapToResponse(AccountEntity account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getId());
        response.setEntityType(account.getEntityType());
        response.setEntityId(account.getEntityId());
        response.setAccountType(account.getAccountType());
        response.setAccountName(account.getAccountName());
        response.setAccountHolderName(account.getAccountHolderName());
        response.setBankName(account.getBankName());
        response.setMobileMoneyService(account.getMobileMoneyService());
        response.setPhoneNumber(account.getPhoneNumber());
        response.setBalance(account.getBalance());
        response.setCurrency(account.getCurrency());
        return response;
    }
}