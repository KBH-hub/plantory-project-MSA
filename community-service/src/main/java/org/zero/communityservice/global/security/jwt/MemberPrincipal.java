package org.zero.communityservice.global.security.jwt;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class MemberPrincipal {
    private final Long memberId;
    private final Collection<? extends GrantedAuthority> authorities;

    public MemberPrincipal(Long memberId, Collection<? extends GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.authorities = authorities;
    }
}
