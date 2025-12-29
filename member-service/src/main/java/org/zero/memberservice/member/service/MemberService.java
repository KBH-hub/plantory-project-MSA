package org.zero.memberservice.member.service;


import org.zero.memberservice.profile.dto.MemberSignUpRequest;

public interface MemberService {
    void signUp(MemberSignUpRequest request);

    boolean isMembernameAvailable(String membername);
    boolean isNicknameAvailable(String nickname);
}
