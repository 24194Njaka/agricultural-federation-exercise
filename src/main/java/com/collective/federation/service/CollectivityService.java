package com.collective.federation.service;

import com.collective.federation.dto.*;
import com.collective.federation.entity.*;
import com.collective.federation.repository.CollectivityRepository;
import com.collective.federation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CollectivityResponse createCollectivity(CreateCollectivityRequest request) {
        // Vérification de l'approbation de la fédération
        if (request.getFederationApproval() == null || !request.getFederationApproval()) {
            throw new RuntimeException("Federation approval is required");
        }

        // Vérification des membres
        List<String> memberIds = request.getMembers();
        if (memberIds == null || memberIds.size() < 10) {
            throw new RuntimeException("Need at least 10 members");
        }

        // Vérifier la structure (président, VP, trésorier, secrétaire)
        CreateCollectivityRequest.CreateCollectivityStructure structureReq = request.getStructure();
        if (structureReq == null) {
            throw new RuntimeException("Structure is required (president, vicePresident, treasurer, secretary)");
        }
        if (structureReq.getPresident() == null) throw new RuntimeException("President is required");
        if (structureReq.getVicePresident() == null) throw new RuntimeException("Vice President is required");
        if (structureReq.getTreasurer() == null) throw new RuntimeException("Treasurer is required");
        if (structureReq.getSecretary() == null) throw new RuntimeException("Secretary is required");

        // Récupérer les membres par leurs IDs
        MemberEntity president = memberRepository.findById(Long.parseLong(structureReq.getPresident()));
        MemberEntity vicePresident = memberRepository.findById(Long.parseLong(structureReq.getVicePresident()));
        MemberEntity treasurer = memberRepository.findById(Long.parseLong(structureReq.getTreasurer()));
        MemberEntity secretary = memberRepository.findById(Long.parseLong(structureReq.getSecretary()));

        if (president == null) throw new RuntimeException("President member not found");
        if (vicePresident == null) throw new RuntimeException("Vice President member not found");
        if (treasurer == null) throw new RuntimeException("Treasurer member not found");
        if (secretary == null) throw new RuntimeException("Secretary member not found");

        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        List<MemberEntity> allRequestMembers = new ArrayList<>();
        for (String idStr : memberIds) {
            MemberEntity m = memberRepository.findById(Long.parseLong(idStr));
            if (m == null) throw new RuntimeException("Member not found: " + idStr);
            allRequestMembers.add(m);
        }

        long seniorCount = allRequestMembers.stream()
                .filter(m -> m.getMembershipDate() != null && m.getMembershipDate().isBefore(sixMonthsAgo))
                .count();

        if (seniorCount < 5) {
            throw new RuntimeException("Need 5 members with 6+ months seniority");
        }

        CollectivityEntity collectivity = new CollectivityEntity();
        collectivity.setNumber(generateNumber());
        collectivity.setName(request.getLocation());
        collectivity.setSpecialty("GENERAL");
        collectivity.setCity(request.getLocation());
        collectivity.setCreationDate(LocalDate.now());
        collectivity.setStatus(TypeStatus.PENDING);

        CollectivityEntity saved = collectivityRepository.save(collectivity);

         for (MemberEntity member : allRequestMembers) {
            member.setCollectivityId(saved.getId());
            memberRepository.save(member);
        }

        president.setRole(MemberRole.PRESIDENT);
        vicePresident.setRole(MemberRole.VICE_PRESIDENT);
        treasurer.setRole(MemberRole.TREASURER);
        secretary.setRole(MemberRole.SECRETARY);
        memberRepository.save(president);
        memberRepository.save(vicePresident);
        memberRepository.save(treasurer);
        memberRepository.save(secretary);

        return buildResponse(saved, president, vicePresident, treasurer, secretary, allRequestMembers);
    }

    private String generateNumber() {
        return "COL" + System.currentTimeMillis();
    }

    private CollectivityResponse buildResponse(CollectivityEntity collectivity,
                                               MemberEntity president,
                                               MemberEntity vicePresident,
                                               MemberEntity treasurer,
                                               MemberEntity secretary,
                                               List<MemberEntity> allMembers) {

        MemberResponse presidentResp = toMemberResponse(president);
        MemberResponse vicePresidentResp = toMemberResponse(vicePresident);
        MemberResponse treasurerResp = toMemberResponse(treasurer);
        MemberResponse secretaryResp = toMemberResponse(secretary);

        CollectivityStructure structure = new CollectivityStructure();
        structure.setPresident(presidentResp);
        structure.setVicePresident(vicePresidentResp);
        structure.setTreasurer(treasurerResp);
        structure.setSecretary(secretaryResp);

        List<MemberResponse> memberResponses = new ArrayList<>();
        for (MemberEntity member : allMembers) {
            memberResponses.add(toMemberResponse(member));
        }

        CollectivityResponse response = new CollectivityResponse();
        response.setId(String.valueOf(collectivity.getId()));
        response.setLocation(collectivity.getCity());
        response.setStructure(structure);
        response.setMembers(memberResponses);

        return response;
    }

    private MemberResponse toMemberResponse(MemberEntity member) {
        MemberResponse resp = new MemberResponse();
        resp.setId(String.valueOf(member.getId()));
        resp.setFirstName(member.getFirstName());
        resp.setLastName(member.getLastName());
        resp.setBirthDate(member.getBirthDate().toString());
        resp.setGender(member.getGender().name());
        resp.setAddress(member.getAddress());
        resp.setProfession(member.getProfession());
        resp.setPhoneNumber(Integer.valueOf(member.getPhoneNumber()));
        resp.setEmail(member.getEmail());
        resp.setOccupation(member.getRole().name());
        return resp;
    }
}