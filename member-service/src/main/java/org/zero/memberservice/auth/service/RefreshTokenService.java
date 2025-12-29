package org.zero.memberservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zero.memberservice.auth.RefreshToken;
import org.zero.memberservice.auth.RefreshTokenRepository;
import org.zero.memberservice.auth.dto.RefreshTokenRequest;
import org.zero.memberservice.global.utils.TokenHashUtil;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenHashUtil tokenHashUtil;

    @Transactional(readOnly = true)
    public RefreshTokenRequest findByRefreshToken(String refreshToken) {
        String tokenHash = tokenHashUtil.hash(refreshToken);

        RefreshToken token = refreshTokenRepository.findByTokenHash(tokenHash)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        return toDto(token);
    }

    @Transactional
    public void deleteByToken(String refreshToken) {
        String tokenHash = tokenHashUtil.hash(refreshToken);
        refreshTokenRepository.deleteByTokenHash(tokenHash);
    }

    @Transactional
    public void deleteByUserId(Long memberId) {
        refreshTokenRepository.deleteByMemberId(memberId);
    }

    @Transactional
    public void save(Long memberId, String refreshToken) {
        String tokenHash = tokenHashUtil.hash(refreshToken);

        RefreshToken token = refreshTokenRepository.findByMemberId(memberId)
                .map(existing -> {
                    existing.setTokenHash(tokenHash);
                    return existing;
                })
                .orElseGet(() -> RefreshToken.builder()
                        .memberId(memberId)
                        .tokenHash(tokenHash)
                        .build()
                );

        refreshTokenRepository.save(token);
    }

    private RefreshTokenRequest toDto(RefreshToken entity) {
        RefreshTokenRequest dto = new RefreshTokenRequest();
        dto.setRefreshTokenId(entity.getId());
        dto.setMemberId(entity.getMemberId());
        dto.setTokenHash(entity.getTokenHash());
        return dto;
    }
}
