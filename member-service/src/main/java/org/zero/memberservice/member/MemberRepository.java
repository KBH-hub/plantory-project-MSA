package org.zero.memberservice.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMembernameAndDelFlagIsNull(String membername);

    boolean existsByNicknameAndDelFlagIsNull(String nickname);

    Optional<Member> findByMembernameAndDelFlagIsNull(String membername);

    Optional<Member> findByMemberIdAndDelFlagIsNull(Long memberId);

    boolean existsByMembername(String username);
    boolean existsByNickname(String nickname);
}
