package org.zero.communityservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.zero.common.member.MemberStatusResponse;

@FeignClient(name = "MEMBER-SERVICE")
public interface MemberInternalClient {

    @GetMapping("/internal/members/{memberId}/status")
    MemberStatusResponse status(
            @PathVariable("memberId") Long memberId,
            @RequestHeader("Authorization") String authorization
    );
}

