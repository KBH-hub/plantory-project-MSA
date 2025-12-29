package org.zero.memberservice.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zero.memberservice.auth.service.RefreshTokenService;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final RefreshTokenService refreshTokenService;

    public void forceLogout(Long memberId) {
        refreshTokenService.deleteByUserId(memberId);
    }
}

