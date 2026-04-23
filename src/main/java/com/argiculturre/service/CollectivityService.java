package com.argiculturre.service;

import com.argiculturre.dto.request.CreateCollectivityRequest;
import com.argiculturre.dto.response.CollectivityResponse;
import com.argiculturre.dto.response.MemberResponse;
import com.argiculturre.entity.*;
import com.argiculturre.repository.AccountRepository;
import com.argiculturre.repository.CollectivityRepository;
import com.argiculturre.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public CollectivityResponse createCollectivity(CreateCollectivityRequest request) {
        if (request.getMembers() == null || request.getMembers().size() < 10) {
            throw new RuntimeException("Need at least 10 members");
        }

         List<Long> memberIds = request.getMembers().stream()
                .map(m -> Long.parseLong(String.valueOf(m.getId())))
                .collect(Collectors.toList());

        List<MemberEntity> existingMembers = memberRepository.findByIds(memberIds);
        if (existingMembers.size() != memberIds.size()) {
            throw new RuntimeException("Some members do not exist");
        }

        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        long seniorCount = existingMembers.stream()
                .filter(m -> m.getMembershipDate() != null && m.getMembershipDate().isBefore(sixMonthsAgo))
                .count();
        if (seniorCount < 5) {
            throw new RuntimeException("Need 5 members with 6+ months seniority");
        }

        boolean hasPresident = request.getMembers().stream().anyMatch(m -> "PRESIDENT".equals(m.getRole()));
        boolean hasVicePresident = request.getMembers().stream().anyMatch(m -> "VICE_PRESIDENT".equals(m.getRole()));
        boolean hasTreasurer = request.getMembers().stream().anyMatch(m -> "TREASURER".equals(m.getRole()));
        boolean hasSecretary = request.getMembers().stream().anyMatch(m -> "SECRETARY".equals(m.getRole()));

        if (!hasPresident) throw new RuntimeException("Missing PRESIDENT");
        if (!hasVicePresident) throw new RuntimeException("Missing VICE_PRESIDENT");
        if (!hasTreasurer) throw new RuntimeException("Missing TREASURER");
        if (!hasSecretary) throw new RuntimeException("Missing SECRETARY");

        CollectivityEntity collectivity = new CollectivityEntity();
        collectivity.setLocation(request.getLocation());
        collectivity.setCreationDate(LocalDate.now());
        collectivity.setStatus(TypeStatus.PENDING);
        CollectivityEntity saved = collectivityRepository.save(collectivity);

        for (CreateCollectivityRequest.MemberInfo memberInfo : request.getMembers()) {
            Long memberId = Long.parseLong(String.valueOf(memberInfo.getId()));
            MemberRole role = MemberRole.valueOf(memberInfo.getRole());
            memberRepository.updateRoleAndCollectivity(memberId, role, saved.getId());
        }

         return buildResponse(saved, existingMembers);
    }

    private CollectivityResponse buildResponse(CollectivityEntity collectivity, List<MemberEntity> members) {
        CollectivityResponse response = new CollectivityResponse();
        response.setId(collectivity.getId());
        response.setNumber(collectivity.getNumber());
        response.setName(collectivity.getName());
        response.setLocation(collectivity.getLocation());
        response.setCreationDate(collectivity.getCreationDate());
        response.setStatus(collectivity.getStatus().name());
        response.setMemberCount(members.size());

        List<MemberResponse> memberResponses = new ArrayList<>();
        for (MemberEntity m : members) {
            MemberResponse mr = new MemberResponse();
            mr.setId(m.getId());
            mr.setFirstName(m.getFirstName());
            mr.setLastName(m.getLastName());
            mr.setRole(m.getRole().name());
            memberResponses.add(mr);
        }
        response.setMembers(memberResponses);

        return response;
    }

    public CollectivityEntity getCollectivityById(Long id) {
        CollectivityEntity collectivity = collectivityRepository.findById(id);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }
        return collectivity;
    }

    public List<FinancialAccountResponse> getFinancialAccounts(Long collectivityId, LocalDate atDate) {
        CollectivityEntity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new RuntimeException("Collectivity not found");
        }
        List<AccountEntity> accounts = accountRepository.findByEntityWithBalanceAtDate("COLLECTIVITY", collectivityId, atDate);
        return accounts.stream().map(this::mapToFinancialAccountResponse).collect(Collectors.toList());
    }

    private FinancialAccountResponse mapToFinancialAccountResponse(AccountEntity account) {
        FinancialAccountResponse response = new FinancialAccountResponse();
        response.setId(account.getId());
        response.setAccountType(account.getAccountType());
        response.setAccountHolderName(account.getAccountName());
        response.setAccountHolderName(account.getAccountHolderName());
        response.setBankName(account.getBankName());
        response.setMobileMoneyService(account.getMobileMoneyService());
        response.setPhoneNumber(account.getPhoneNumber());
        response.setBalanceAtDate(account.getBalance());
        response.setCurrency(account.getCurrency());
        return response;
    }

    public CollectivityResponse mapToResponse(CollectivityEntity collectivity) {
        CollectivityResponse response = new CollectivityResponse();
        response.setId(collectivity.getId());
        response.setNumber(collectivity.getNumber());
        response.setName(collectivity.getName());
        response.setLocation(collectivity.getLocation());
        response.setCreationDate(collectivity.getCreationDate());
        response.setStatus(collectivity.getStatus().name());
        response.setMemberCount(collectivityRepository.countMembers(collectivity.getId()));
        // Ajouter les membres si nécessaire
        return response;
    }
}