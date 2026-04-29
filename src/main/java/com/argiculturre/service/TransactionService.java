package com.argiculturre.service;

import com.argiculturre.dto.request.CreateTransactionRequest;
import com.argiculturre.dto.response.CashFlowResponse;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
         AccountEntity account = accountRepository.findById(request.getAccountId());
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

         MemberEntity member = memberRepository.findById(request.getMemberId());
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountId(request.getAccountId());
        transaction.setMemberId(request.getMemberId());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setAmount(request.getAmount());
        transaction.setPaymentMethod(request.getPaymentMethod());
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setDescription(request.getDescription());

        TransactionEntity saved = transactionRepository.save(transaction);

        double newBalance = account.getBalance() +
                ("CONTRIBUTION".equals(request.getTransactionType()) ? request.getAmount() : -request.getAmount());
        accountRepository.updateBalance(account.getId(), newBalance);

        return mapToResponse(saved, member);
    }

    public CashFlowResponse getCashFlow(String collectivityId, LocalDate startDate, LocalDate endDate) {
        List<AccountEntity> accounts = accountRepository.findByEntity("COLLECTIVITY", collectivityId);

        double totalIncome = 0;
        double totalExpense = 0;
        List<TransactionResponse> allTransactions = new ArrayList<>();

        for (AccountEntity account : accounts) {
            List<TransactionEntity> transactions = transactionRepository.findByCollectivityId(account.getId(), startDate, endDate);

            for (TransactionEntity t : transactions) {
                MemberEntity member = memberRepository.findById(t.getMemberId());
                if ("CONTRIBUTION".equals(t.getTransactionType())) {
                    totalIncome += t.getAmount();
                } else {
                    totalExpense += t.getAmount();
                }
                allTransactions.add(mapToResponse(t, member));
            }
        }

        CashFlowResponse response = new CashFlowResponse();
        response.setCollectivityId(collectivityId);
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        response.setTotalIncome(totalIncome);
        response.setTotalExpense(totalExpense);
        response.setBalance(totalIncome - totalExpense);
        response.setTransactions(allTransactions);

        return response;
    }

    private TransactionResponse mapToResponse(TransactionEntity transaction, MemberEntity member) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setAccountId(transaction.getAccountId());
        response.setMemberId(transaction.getMemberId());
        response.setMemberName(member.getFirstName() + " " + member.getLastName());
        response.setTransactionType(transaction.getTransactionType());
        response.setAmount(transaction.getAmount());
        response.setPaymentMethod(transaction.getPaymentMethod());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setDescription(transaction.getDescription());
        return response;
    }

    public List<TransactionResponse> getCollectivityTransactions(String collectivityId, LocalDate from, LocalDate to) {
        List<TransactionEntity> transactions = transactionRepository.findByCollectivityId(collectivityId, from, to);
        return transactions.stream().map(this::mapToResponse).collect(Collectors.toList());
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