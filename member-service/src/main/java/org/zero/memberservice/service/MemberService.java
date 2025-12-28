package org.zero.memberservice.service;


import org.zero.memberservice.dto.MemberSignUpRequest;
import org.zero.memberservice.dto.MemberStatusResponse;

public interface MemberService {
    boolean isDuplicateMembername(String membername);
    boolean isDuplicateNickname(String nickname);
    void signUp(MemberSignUpRequest request);

    MemberStatusResponse getStatus(Long memberId);
}
