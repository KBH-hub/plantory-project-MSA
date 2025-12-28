package org.zero.communityservice.sharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zero.communityservice.sharing.service.SharingScoreServiceImpl;

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