package org.zero.plantservice.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.zero.plantservice.global.security.MemberPrincipal;

import java.security.Key;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("ExpiredJwtException: ", e);
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("JwtException: ", e);
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(jwtProperties.getIssuer())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        Long memberId = Long.valueOf(claims.getSubject());

        @SuppressWarnings("unchecked")
        List<String> roles = claims.get("roles", List.class);
        if (roles == null) roles = List.of();

        var principal = new MemberPrincipal(memberId, roles);

        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
