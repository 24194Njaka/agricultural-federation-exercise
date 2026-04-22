package com.collective.federation.service;

import com.collective.federation.dto.CollectivityResponse;
import com.collective.federation.entity.*;
import com.collective.federation.repository.CollectivityRepository;
import com.collective.federation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CollectivityResponse createCollectivity(CollectivityEntity request) {
        // Vérifications
        if (collectivityRepository.existsByNumber(request.getNumber()))
            throw new RuntimeException("Number already exists");
        if (collectivityRepository.existsByName(request.getName()))
            throw new RuntimeException("Name already exists");
        if (request.getMembers().size() < 10)
            throw new RuntimeException("Need at least 10 members");

        // Vérifier 5 membres avec +6 mois d'ancienneté
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        long seniorCount = request.getMembers().stream()
                .filter(m -> m.getMembershipDate() != null && m.getMembershipDate().isBefore(sixMonthsAgo))
                .count();
        if (seniorCount < 5)
            throw new RuntimeException("Need 5 members with 6+ months seniority");

        // Vérifier les rôles spécifiques
        if (request.getMembers().stream().noneMatch(m -> "PRESIDENT".equals(m.getRole())))
            throw new RuntimeException("Missing PRESIDENT");
        if (request.getMembers().stream().noneMatch(m -> "VICE_PRESIDENT".equals(m.getRole())))
            throw new RuntimeException("Missing VICE_PRESIDENT");
        if (request.getMembers().stream().noneMatch(m -> "TREASURER".equals(m.getRole())))
            throw new RuntimeException("Missing TREASURER");
        if (request.getMembers().stream().noneMatch(m -> "SECRETARY".equals(m.getRole())))
            throw new RuntimeException("Missing SECRETARY");

        // Créer la collectivité
        CollectivityEntity collectivity = new CollectivityEntity();
        collectivity.setNumber(request.getNumber());
        collectivity.setName(request.getName());
        collectivity.setSpecialty(request.getSpecialty());
        collectivity.setCity(request.getCity());
        collectivity.setCreationDate(request.getCreationDate());
        collectivity.setStatus(TypeStatus.PENDING);
        CollectivityEntity saved = collectivityRepository.save(collectivity);

        // Créer les membres
        for (var m : request.getMembers()) {
            MemberEntity member = new MemberEntity();
            member.setFirstName(m.getFirstName());
            member.setLastName(m.getLastName());
            member.setBirthDate(m.getBirthDate());
            member.setGender(TypeGender.valueOf(m.getGender()));
            member.setAddress(m.getAddress());
            member.setProfession(m.getProfession());
            member.setPhoneNumber(m.getPhone());
            member.setEmail(m.getEmail());
            member.setMembershipDate(LocalDate.now());
            member.setRole(MemberRole.valueOf(m.getRole()));
            member.setCollectivityId(saved.getId());
            memberRepository.save(member);
        }

        // Réponse
        CollectivityResponse resp = new CollectivityResponse();
        resp.setId(saved.getId());
        resp.setNumber(saved.getNumber());
        resp.setName(saved.getName());
        resp.setSpecialty(saved.getSpecialty());
        resp.setCity(saved.getCity());
        resp.setCreationDate(saved.getCreationDate());
        resp.setStatus(saved.getStatus().name());
        resp.setMemberCount(collectivityRepository.countMembers(saved.getId()));
        return resp;
    }
}