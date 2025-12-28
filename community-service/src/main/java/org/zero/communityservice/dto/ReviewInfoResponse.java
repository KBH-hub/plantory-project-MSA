package org.zero.communityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zero.communityservice.service.SharingScoreServiceImpl;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewInfoResponse {
    private Long sharingId;
    private Long partnerId;
    private String partnerNickname;
    private String title;
    private LocalDateTime createdAt;
    private SharingScoreServiceImpl.ReviewerType reviewerType;
}