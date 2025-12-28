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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zero.memberservice.global.security.MemberPrincipal;
import org.zero.memberservice.image.service.ImageService;
import org.zero.memberservice.profile.dto.PasswordChangeRequest;
import org.zero.memberservice.profile.dto.ProfileInfoResponse;
import org.zero.memberservice.profile.dto.ProfileUpdateRequest;
import org.zero.memberservice.profile.dto.PublicProfileResponse;
import org.zero.memberservice.profile.service.ProfileService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "프로필 API")
public class ProfileRestController {

    private final ProfileService profileService;
    private final ImageService imageService;

    @Operation(summary = "내 프로필 조회", description = "로그인한 사용자의 프로필 정보를 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 필요"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @GetMapping("/me")
    public ResponseEntity<ProfileInfoResponse> getProfile(
            @RequestHeader(value = "X-User-Id", required = false) Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(profileService.getProfileInfo(userId));
    }



    @Operation(summary = "타인 프로필 조회", description = "회원 ID로 공개 프로필 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/publicProfile/{memberId}")
    public ResponseEntity<PublicProfileResponse> getPublicProfile(
            @Parameter(description = "회원 ID", example = "8")
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(profileService.getPublicProfile(memberId));
    }

    @Operation(summary = "비밀번호 변경", description = "기존 비밀번호 확인 후 새 비밀번호로 변경합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "변경 처리 성공"),
            @ApiResponse(responseCode = "401", description = "인증 필요"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestHeader(value = "X-User-Id", required = false) Long userId,
            @RequestBody PasswordChangeRequest req
    ) {
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }

        boolean success = profileService.changePassword(
                userId,
                req.getOldPassword(),
                req.getNewPassword()
        );
        return ResponseEntity.ok(Map.of("success", success));
    }


    @Operation(summary = "회원 탈퇴", description = "로그인한 사용자를 소프트 삭제 처리합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "탈퇴 성공"),
            @ApiResponse(responseCode = "401", description = "인증 필요"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @PutMapping("/withdraw")
    public ResponseEntity<?> softWithdraw(
            @RequestHeader(value = "X-User-Id", required = false) Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        profileService.deleteMemberById(userId);
        return ResponseEntity.ok("회원 탈퇴 완료");
    }


    @Operation(summary = "프로필 수정", description = "로그인한 사용자의 프로필 정보를 수정합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증 필요"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @PutMapping
    public ResponseEntity<?> updateProfile(
            @RequestHeader(value = "X-User-Id", required = false) Long userId,
            @RequestBody ProfileUpdateRequest profileUpdateRequest
    ) {
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        profileUpdateRequest.setMemberId(userId);
        profileService.updateProfileInfo(profileUpdateRequest);
        return ResponseEntity.ok("success");
    }


    @Operation(summary = "프로필 사진 업로드", description = "프로필 사진을 업로드하고 imageUrl을 반환합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "업로드 성공"),
            @ApiResponse(responseCode = "400", description = "업로드 실패"),
            @ApiResponse(responseCode = "401", description = "인증 필요"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @PostMapping("/picture")
    public Map<String, Object> uploadProfile(
            @RequestHeader(value = "X-User-Id", required = false) Long userId,
            @RequestParam("profileImage") MultipartFile file
    ) throws IOException {
        if (userId == null) {
            // Map 반환 형태 유지
            return Map.of("success", false, "message", "로그인이 필요합니다.");
        }

        String imageUrl = imageService.uploadProfileImage(userId, file);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("imageUrl", imageUrl);
        return response;
    }


    @Operation(summary = "프로필 사진 조회", description = "회원 ID로 프로필 사진 URL을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/picture")
    public Map<String, Object> getProfileImage(
            @Parameter(description = "회원 ID", example = "8")
            @RequestParam Long memberId
    ) {
        String imageUrl = imageService.getProfileImageUrl(memberId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("imageUrl", imageUrl);
        return response;
    }
}
