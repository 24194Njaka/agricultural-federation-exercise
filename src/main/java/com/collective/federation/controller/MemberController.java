package com.collective.federation.controller;

import com.collective.federation.entity.Member;
import com.collective.federation.service.MemberService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            memberService.registerMember(request.getMember(), request.getRefereeIds());
            return ResponseEntity.ok("Member registered successfully under B-2 rules!");
        } catch (RuntimeException e) {
            // Renvoie l'erreur si les parrains ne sont pas valides ou frais non payés
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Classe simple pour recevoir le JSON
    @Data
    public static class RegistrationRequest {
        private Member member;
        private List<String> refereeIds;
    }
}