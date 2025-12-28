package org.zero.memberservice.dto;

public record MemberStatusResponse(
        Long memberId,
        boolean exists,
        boolean stopped,
        String stopUntil
) {}

