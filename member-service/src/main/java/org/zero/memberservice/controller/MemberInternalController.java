package org.zero.memberservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.memberservice.dto.MemberStatusResponse;
import org.zero.memberservice.service.MemberService;

@RestController
@RequestMapping("/internal/members")
@RequiredArgsConstructor
public class MemberInternalController {

    private final MemberService memberService;

    @GetMapping("/{memberId}/status")
    public MemberStatusResponse status(@PathVariable Long memberId) {
        return memberService.getStatus(memberId);
    }
}

