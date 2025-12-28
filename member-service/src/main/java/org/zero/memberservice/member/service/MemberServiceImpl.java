package org.zero.memberservice.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zero.memberservice.member.Member;
import org.zero.memberservice.member.MemberRepository;
import org.zero.memberservice.profile.dto.MemberSignUpRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void signUp(MemberSignUpRequest request) {

        if (memberRepository.existsByMembernameAndDelFlagIsNull(request.getMembername())) {
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }
        if (memberRepository.existsByNicknameAndDelFlagIsNull(request.getNickname())) {
            throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        Member member = Member.createForSignUp(
                request.getMembername(),
                encodedPassword,
                request.getNickname(),
                request.getPhone(),
                request.getAddress()
        );

        memberRepository.save(member);
    }

    @Override
    public boolean isMembernameAvailable(String membername) {
        return !memberRepository.existsByMembername(membername);
    }

    @Override
    public boolean isNicknameAvailable(String nickname) {
        return !memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        return memberRepository.findByMemberIdAndDelFlagIsNull(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원"));
    }
}
