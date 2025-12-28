package org.zero.memberservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zero.memberservice.Member;
import org.zero.memberservice.MemberRepository;
import org.zero.memberservice.dto.MemberSignUpRequest;
import org.zero.memberservice.dto.MemberStatusResponse;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public boolean isDuplicateMembername(String membername) {
        return memberRepository.existsByMembernameAndDelFlagIsNull(membername);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isDuplicateNickname(String nickname) {
        return memberRepository.existsByNicknameAndDelFlagIsNull(nickname);
    }

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
    public MemberStatusResponse getStatus(Long memberId) {
        return memberRepository.findByMemberIdAndDelFlagIsNull(memberId)
                .map(m -> {
                    LocalDateTime stopDay = m.getStopDay();
                    boolean stopped = stopDay != null && stopDay.isAfter(LocalDateTime.now());
                    return new MemberStatusResponse(
                            memberId,
                            true,
                            stopped,
                            stopDay == null ? null : stopDay.toString()
                    );
                })
                .orElse(new MemberStatusResponse(memberId, false, false, null));
    }
}
