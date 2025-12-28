package org.zero.plantservice.global.security;

import java.io.Serializable;
import java.util.List;

//public class MemberPrincipal implements UserDetails {
public record MemberPrincipal(Long memberId, List<String> roles) implements Serializable {
}