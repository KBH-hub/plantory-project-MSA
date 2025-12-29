package org.zero.memberservice.presentation.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberStatusResponse {

    private Long memberId;
    private boolean stopped;
}
