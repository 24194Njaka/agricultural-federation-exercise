package com.collective.federation.controller;

import com.collective.federation.entity.MemberEntity;
import com.collective.federation.service.MemberService;
import com.collective.federation.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            memberService.registerMember(request.getMember(), request.getRefereeIds());
            return ResponseEntity.ok("Member registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Integer> getCount(@PathVariable Long id) {
        int count = memberRepository.countMembersByCollectivity(id);
        return ResponseEntity.ok(count);
    }

    public static class RegistrationRequest {
        private MemberEntity member;
        private List<String> refereeIds;

        public RegistrationRequest() {}

        public MemberEntity getMember() {
            return member;
        }

        public void setMember(MemberEntity member) {
            this.member = member;
        }

        public List<String> getRefereeIds() {
            return refereeIds;
        }

        public void setRefereeIds(List<String> refereeIds) {
            this.refereeIds = refereeIds;
        }
    }
}