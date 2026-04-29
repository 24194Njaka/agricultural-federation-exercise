package com.argiculturre.service;

import com.argiculturre.dto.request.CreatePaymentRequest;
import com.argiculturre.dto.response.TransactionResponse;
import com.argiculturre.entity.AccountEntity;
import com.argiculturre.entity.MemberEntity;
import com.argiculturre.entity.TransactionEntity;
import com.argiculturre.repository.AccountRepository;
import com.argiculturre.repository.MemberRepository;
import com.argiculturre.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<TransactionResponse> createPayments(String memberId, List<CreatePaymentRequest> requests) {
        MemberEntity member = memberRepository.findById(memberId);
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        return requests.stream()
                .map(req -> createPayment(memberId, req))
                .collect(Collectors.toList());
    }

    private TransactionResponse createPayment(String memberId, CreatePaymentRequest request) {
        AccountEntity account = accountRepository.findById(request.getAccountId());
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountId(request.getAccountId());
        transaction.setMemberId(memberId);
        transaction.setTransactionType("PAYMENT");
        transaction.setAmount(request.getAmount());
        transaction.setPaymentMethod(request.getPaymentMethod());
        transaction.setTransactionDate(request.getPaymentDate());
        transaction.setDescription(request.getDescription());

        TransactionEntity saved = transactionRepository.save(transaction);

         double newBalance = account.getBalance() - request.getAmount();
        accountRepository.updateBalance(account.getId(), newBalance);

        return mapToResponse(saved);
    }

    private TransactionResponse mapToResponse(TransactionEntity transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setAccountId(transaction.getAccountId());
        response.setMemberId(transaction.getMemberId());
        response.setTransactionType(transaction.getTransactionType());
        response.setAmount(transaction.getAmount());
        response.setPaymentMethod(transaction.getPaymentMethod());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setDescription(transaction.getDescription());
        return response;
    }
}