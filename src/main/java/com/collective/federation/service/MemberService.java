package com.collective.federation.service;

import com.collective.federation.dto.CreateMemberRequest;
import com.collective.federation.dto.MemberResponse;
import com.collective.federation.entity.*;
import com.collective.federation.repository.CollectivityRepository;
import com.collective.federation.repository.MemberRepository;
import com.collective.federation.repository.SponsorshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;
    private final SponsorshipRepository sponsorshipRepository;

    @Transactional
    public MemberResponse createMember(CreateMemberRequest request) {
        // Vérification email
        if (memberRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists");

        // Vérification collectivité
        CollectivityEntity collectivity = collectivityRepository.findById(request.getCollectivityId());
        if (collectivity == null)
            throw new RuntimeException("Collectivity not found");

        // Vérification nombre de parrains
        if (request.getSponsors() == null || request.getSponsors().size() < 2)
            throw new RuntimeException("Need at least 2 sponsors");

        // Vérification des parrains
        int fromTarget = 0, fromOther = 0;
        List<MemberEntity> sponsorEntities = new ArrayList<>();

        for (var s : request.getSponsors()) {
            MemberEntity sponsor = memberRepository.findById(s.getSponsorId());
            if (sponsor == null)
                throw new RuntimeException("Sponsor not found: " + s.getSponsorId());
            if (sponsor.getRole() != MemberRole.CONFIRMED_MEMBER)
                throw new RuntimeException("Sponsor must be CONFIRMED_MEMBER");
            if (sponsor.getMembershipDate().plusDays(90).isAfter(LocalDate.now()))
                throw new RuntimeException("Sponsor needs 90+ days seniority");

            if (sponsor.getCollectivityId().equals(collectivity.getId()))
                fromTarget++;
            else
                fromOther++;

            sponsorEntities.add(sponsor);
        }

        if (fromTarget < fromOther)
            throw new RuntimeException("Sponsors from target collectivity must be >= others");

        // Création du membre
        MemberEntity member = new MemberEntity();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setBirthDate(request.getBirthDate());
        member.setGender(request.getGender());
        member.setAddress(request.getAddress());
        member.setProfession(request.getProfession());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setEmail(request.getEmail());
        member.setMembershipDate(LocalDate.now());
        member.setRole(MemberRole.JUNIOR_MEMBER);
        member.setCollectivityId(collectivity.getId());
        MemberEntity saved = memberRepository.save(member);

        // Création des parrainages
        for (var s : request.getSponsors()) {
            SponsorshipEntity sponsorship = new SponsorshipEntity();
            sponsorship.setMemberId(saved.getId());
            sponsorship.setSponsorId(s.getSponsorId());
            sponsorship.setRelationship(s.getRelationship());
            sponsorship.setSponsorshipDate(LocalDate.now());
            sponsorshipRepository.save(sponsorship);
        }

        // Construction de la liste des referees (parrains)
        List<MemberResponse> refereesList = new ArrayList<>();
        for (MemberEntity sponsor : sponsorEntities) {
            MemberResponse refereeResp = new MemberResponse();
            refereeResp.setId(String.valueOf(sponsor.getId()));
            refereeResp.setFirstName(sponsor.getFirstName());
            refereeResp.setLastName(sponsor.getLastName());
            refereeResp.setBirthDate(sponsor.getBirthDate().toString());
            refereeResp.setGender(sponsor.getGender().name());
            refereeResp.setAddress(sponsor.getAddress());
            refereeResp.setProfession(sponsor.getProfession());
            refereeResp.setPhoneNumber(Integer.valueOf(sponsor.getPhoneNumber()));
            refereeResp.setEmail(sponsor.getEmail());
            refereeResp.setOccupation(sponsor.getRole().name());
            refereesList.add(refereeResp);
        }


        MemberResponse resp = new MemberResponse();
        resp.setId(String.valueOf(saved.getId()));
        resp.setFirstName(saved.getFirstName());
        resp.setLastName(saved.getLastName());
        resp.setBirthDate(saved.getBirthDate().toString());
        resp.setGender(saved.getGender().name());
        resp.setAddress(saved.getAddress());
        resp.setProfession(saved.getProfession());
        resp.setPhoneNumber(Integer.valueOf(saved.getPhoneNumber()));
        resp.setEmail(saved.getEmail());
        resp.setOccupation(saved.getRole().name());
        resp.setReferees(refereesList);

        return resp;
    }
}