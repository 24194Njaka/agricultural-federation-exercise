package com.collective.federation.service;

import com.collective.federation.entity.Member;
import com.collective.federation.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional // 
    public void registerMember(Member candidate, List<String> refereeIds) {
        if (!candidate.isRegistrationFeePaid() || !candidate.isMembershipDuesPaid()) {
            throw new RuntimeException("Admission refused: Fees (50,000 MGA) and Dues must be paid.");
        }

        if (refereeIds == null || refereeIds.size() < 2) {
            throw new RuntimeException("Admission refused: At least 2 referees are required.");
        }

        int localReferees = 0;
        int externalReferees = 0;

        for (String refereeId : refereeIds) {
            String refereeCollectivity = memberRepository.getCollectivityIdByMemberId(refereeId);
            if (refereeCollectivity.equals(candidate.getCollectivityId())) {
                localReferees++;
            } else {
                externalReferees++;
            }
        }

        if (localReferees < externalReferees) {
            throw new RuntimeException("Admission refused: Local referees must be greater or equal to external referees.");
        }

        // si tout OK, on sauvegarde
        memberRepository.save(candidate);
        for (String refereeId : refereeIds) {
            memberRepository.saveReferee(candidate.getId(), refereeId, "colleague");
        }
    }
}
