package org.zero.communityservice.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zero.communityservice.image.dto.ImageDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectSharingDetailResponse {
    private Long sharingId;
    private Long memberId;
    private Long targetMemberId;
    private String nickname;
    private BigDecimal sharingRate;

    private String title;
    private String content;
    private String plantType;
    private String managementLevel;
    private String managementNeeds;
    private String managementLevelLabel;
    private String managementNeedsLabel;
    private Integer interestNum;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<ImageDTO> images;
    private boolean interested;


    private LocalDateTime reviewFlag;
    private LocalDateTime receiverReviewFlag;
}
