package org.zero.memberservice.presentation.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zero.memberservice.member.Member;
import org.zero.memberservice.member.MemberRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/internal/members")
@RequiredArgsConstructor
public class InternalMemberRestController {

    private final MemberRepository memberRepository;

    @GetMapping("/{memberId}/status")
    public MemberStatusResponse status(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        boolean stopped = member.getStopDay() != null
                && member.getStopDay().isAfter(LocalDateTime.now());

        return new MemberStatusResponse(member.getMemberId(), stopped);
    }
}
