package org.zero.memberservice.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zero.memberservice.member.service.MemberService;
import org.zero.memberservice.profile.dto.MemberSignUpRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
@Tag(name = "Member", description = "회원 가입 및 중복 체크 API")
public class MemberRestController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입", description = "신규 회원을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "요청값 오류"),
            @ApiResponse(responseCode = "409", description = "중복된 회원 정보")
    })
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(
            @RequestBody MemberSignUpRequest request
    ) {
        memberService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "아이디 사용 가능 여부 확인", description = "회원 아이디(username)의 사용 가능 여부를 확인합니다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "조회 성공"), @ApiResponse(responseCode = "400", description = "요청값 오류")
    })
    @GetMapping("/checkMembername")
    public ResponseEntity<Map<String, Boolean>> checkUsername(
            @Parameter(description = "회원 아이디", example = "soo8848")
            @RequestParam("membername") @NotBlank String value
    ) {
        boolean available = memberService.isMembernameAvailable(value.trim());
        return ResponseEntity.ok(Map.of("available", available));
    }

    @Operation(summary = "닉네임 사용 가능 여부 확인", description = "회원 닉네임의 사용 가능 여부를 확인합니다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "조회 성공"), @ApiResponse(responseCode = "400", description = "요청값 오류")})
    @GetMapping("/checkNickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(
            @Parameter(description = "닉네임", example = "제육볶음밥")
            @RequestParam("nickname") @NotBlank String value
    ) {
        boolean available = memberService.isNicknameAvailable(value.trim());
        return ResponseEntity.ok(Map.of("available", available));
    }
}