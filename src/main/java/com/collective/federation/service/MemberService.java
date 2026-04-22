package com.collective.federation.service;
import com.collective.federation.entity.MemberEntity;
import com.collective.federation.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    @Transactional
    public void registerMember(MemberEntity candidate, List<String> refereeIds) {
        if (refereeIds == null || refereeIds.size() != 2) {
            throw new RuntimeException("Rule B-2: Exactly 2 referees required!");
        }
        memberRepository.save(candidate);
        for (String refId : refereeIds) {
            Long refCollId = memberRepository.getCollectivityIdByMemberId(refId);
            if (refCollId == null) throw new RuntimeException("Referee " + refId + " not found");
            String rel = refCollId.equals(candidate.getCollectivityId()) ? "INTERNAL" : "EXTERNAL";
            memberRepository.saveReferee(candidate.getId(), refId, rel);
        }
    }
}