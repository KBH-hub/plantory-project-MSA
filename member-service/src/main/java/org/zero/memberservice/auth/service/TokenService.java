package org.zero.memberservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zero.memberservice.global.security.jwt.TokenProvider;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    public String createNewAccessToken(String refreshToken) {

//        if (tokenProvider.validateToken(refreshToken)) {
//            throw new IllegalArgumentException("Unexpected token");
//        }

        Long userId = refreshTokenService
                .findByRefreshToken(refreshToken)
                .getMemberId();

        return tokenProvider.createAccessToken(String.valueOf(userId));
    }
}


