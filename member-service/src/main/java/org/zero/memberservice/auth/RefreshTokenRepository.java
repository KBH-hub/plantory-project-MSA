package org.zero.memberservice.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMemberId(Long memberId);

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    void deleteByMemberId(Long memberId);

    void deleteByTokenHash(String tokenHash);
}

