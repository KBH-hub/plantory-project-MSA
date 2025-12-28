package org.zero.communityservice.service;


import org.zero.communityservice.dto.*;

import java.util.List;

public interface SharingReadService {
    List<SharingCardListResponse> getSharingList(SharingSearchRequest request);

    int countInterest(Long memberId);

    List<SharingPopularResponse> getPopularSharingList(SharingSearchRequest request);

    SelectSharingDetailResponse getSharingDetail(Long sharingId, Long memberId);

    List<SelectCommentListResponse> getSharingComments(Long sharingId);

    List<SharingPartnerResponse> getMessagePartners(Long receiverId, Long sharingId);

    List<SharingHistoryResponse> getMySharingGiven(Long memberId);

    List<SharingHistoryResponse> getMySharingReceived(Long memberId);

    ReviewInfoResponse getReviewInfo(Long sharingId, Long memberId);
}
