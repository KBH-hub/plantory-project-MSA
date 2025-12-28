package org.zero.communityservice.global.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

//public class MemberPrincipal implements UserDetails {
public record MemberPrincipal(Long memberId, List<String> roles) implements Serializable {
}