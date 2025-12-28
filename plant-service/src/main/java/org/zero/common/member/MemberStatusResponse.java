package org.zero.common.member;

public record MemberStatusResponse(
        Long memberId,
        boolean exists,
        boolean stopped,
        String stopUntil
) {}
