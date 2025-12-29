package org.zero.memberservice.profile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.memberservice.global.security.MemberPrincipal;
import org.zero.memberservice.profile.dto.ProfileInterestListRequest;
import org.zero.memberservice.profile.dto.ProfileSharingResponse;
import org.zero.memberservice.profile.service.ProfileInterestService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profileInterest")
@Tag(name = "ProfileInterest", description = "프로필 관심 나눔 API")
public class ProfileInterestRestController {

    private final ProfileInterestService profileInterestService;

    @Operation(summary = "관심 나눔 목록 조회", description = "로그인한 사용자가 관심 표시한 나눔 목록을 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 필요"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @GetMapping
    public ResponseEntity<List<ProfileSharingResponse>> getProfileInterest(
            @AuthenticationPrincipal MemberPrincipal principal,
            @Parameter(description = "검색 키워드", example = "몬스테라")
            @RequestParam(required = false) String keyword,
            @Parameter(description = "조회 개수", example = "10")
            @RequestParam int limit,
            @Parameter(description = "조회 시작 위치", example = "0")
            @RequestParam int offset
    ) {
        ProfileInterestListRequest request = new ProfileInterestListRequest();
        request.setMemberId(principal.getMemberId());
        request.setKeyword(keyword);
        request.setLimit(limit);
        request.setOffset(offset);

        List<ProfileSharingResponse> list =
                profileInterestService.getProfileInterest(request);

        return ResponseEntity.ok(list);
    }
}
