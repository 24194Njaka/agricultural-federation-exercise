package com.argiculturre.service;

import com.argiculturre.dto.request.CreateMemberRequest;
import com.argiculturre.dto.response.MemberResponse;
import com.argiculturre.entity.CollectivityEntity;
import com.argiculturre.entity.MemberEntity;
import com.argiculturre.entity.MemberRole;
import com.argiculturre.entity.SponsorshipEntity;
import com.argiculturre.entity.TypeGender;
import com.argiculturre.repository.CollectivityRepository;
import com.argiculturre.repository.MemberRepository;
import com.argiculturre.repository.SponsorshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;
    private final SponsorshipRepository sponsorshipRepository;

    @Transactional
    public MemberResponse createMember(CreateMemberRequest request) {
        // 1. Vérifier email unique
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 2. Vérifier que la collectivité existe
        CollectivityEntity collectivity = null;
        if (request.getCollectivityId() != null) {
            collectivity = collectivityRepository.findById(request.getCollectivityId());
            if (collectivity == null) {
                throw new RuntimeException("Collectivity not found");
            }
        }

        if (request.getCollectivityId() != null && (request.getSponsors() == null || request.getSponsors().size() < 2)) {
            throw new RuntimeException("Need at least 2 sponsors to join a collectivity");
        }

        int fromTarget = 0, fromOther = 0;
        for (CreateMemberRequest.SponsorInfo s : request.getSponsors()) {
            MemberEntity sponsor = memberRepository.findById(s.getSponsorId());
            if (sponsor == null) {
                throw new RuntimeException("Sponsor not found: " + s.getSponsorId());
            }
            if (sponsor.getRole() != MemberRole.CONFIRMED_MEMBER) {
                throw new RuntimeException("Sponsor must be CONFIRMED_MEMBER");
            }
            if (sponsor.getMembershipDate().plusDays(90).isAfter(LocalDate.now())) {
                throw new RuntimeException("Sponsor needs 90+ days seniority");
            }

            if (collectivity != null && sponsor.getCollectivityId() != null
                    && sponsor.getCollectivityId().equals(collectivity.getId())) {
                fromTarget++;
            } else {
                fromOther++;
            }
        }

        if (collectivity != null && fromTarget < fromOther) {
            throw new RuntimeException("Sponsors from target collectivity must be >= others");
        }

         MemberEntity member = new MemberEntity();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setBirthDate(request.getBirthDate());
        member.setGender(TypeGender.valueOf(request.getGender()));
        member.setAddress(request.getAddress());
        member.setProfession(request.getProfession());
        member.setPhoneNumber(request.getPhone());
        member.setEmail(request.getEmail());
        member.setMembershipDate(LocalDate.now());
        member.setRole(MemberRole.JUNIOR_MEMBER);
        if (collectivity != null) {
            member.setCollectivityId(collectivity.getId());
        }

        MemberEntity saved = memberRepository.save(member);

        for (CreateMemberRequest.SponsorInfo s : request.getSponsors()) {
            SponsorshipEntity sponsorship = new SponsorshipEntity();
            sponsorship.setMemberId(saved.getId());
            sponsorship.setSponsorId(s.getSponsorId());
            sponsorship.setRelationship(s.getRelationship());
            sponsorship.setSponsorshipDate(LocalDate.now());
            sponsorshipRepository.save(sponsorship);
        }

         return buildResponse(saved, collectivity);
    }

    private MemberResponse buildResponse(MemberEntity member, CollectivityEntity collectivity) {
        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setFirstName(member.getFirstName());
        response.setLastName(member.getLastName());
        response.setBirthDate(member.getBirthDate());
        response.setGender(member.getGender().name());
        response.setAddress(member.getAddress());
        response.setProfession(member.getProfession());
        response.setPhone(member.getPhoneNumber());
        response.setEmail(member.getEmail());
        response.setMembershipDate(member.getMembershipDate());
        response.setRole(member.getRole().name());
        if (collectivity != null) {
            response.setCollectivityId(collectivity.getId());
        }
        return response;
    }
    public List<MemberEntity> getAllMembers() {
        return memberRepository.findAll();
    }

    public MemberEntity getMemberById(Long id) {
        MemberEntity member = memberRepository.findById(id);
        if (member == null) {
            throw new RuntimeException("Member not found");
        }
        return member;
    }

    public MemberResponse mapToResponse(MemberEntity member) {
        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setFirstName(member.getFirstName());
        response.setLastName(member.getLastName());
        response.setBirthDate(member.getBirthDate());
        response.setGender(member.getGender().name());
        response.setAddress(member.getAddress());
        response.setProfession(member.getProfession());
        response.setPhone(member.getPhoneNumber());
        response.setEmail(member.getEmail());
        response.setMembershipDate(member.getMembershipDate());
        response.setRole(member.getRole().name());
        if (member.getCollectivityId() != null) {
            response.setCollectivityId(member.getCollectivityId());
        }
        return response;
    }

}