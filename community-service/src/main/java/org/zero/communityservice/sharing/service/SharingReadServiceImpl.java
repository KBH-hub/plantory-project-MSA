package org.zero.communityservice.sharing.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zero.communityservice.sharing.SharingMapper;
import org.zero.communityservice.sharing.dto.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharingReadServiceImpl implements SharingReadService {

    private final SharingMapper sharingMapper;

    @Override
    public List<SharingCardListResponse> getSharingList(SharingSearchRequest request) {
        return sharingMapper.selectSharingListByAddressAndKeyword(request);
    }

    @Override
    public int countInterest(Long memberId) {
        return sharingMapper.countInterestByMemberId(memberId);
    }

    @Override
    public List<SharingPopularResponse> getPopularSharingList(SharingSearchRequest request) {
        return sharingMapper.selectPopularSharingList(request);
    }

    @Override
    public SelectSharingDetailResponse getSharingDetail(Long sharingId, Long memberId) {
        return null;
    }

    @Override
    public List<SelectCommentListResponse> getSharingComments(Long sharingId) {
        return sharingMapper.selectSharingComments(sharingId);
    }

    @Override
    public List<SharingPartnerResponse> getMessagePartners(Long receiverId, Long sharingId) {
        return sharingMapper.selectSharingMessagePartners(receiverId, sharingId);
    }

    @Override
    public List<SharingHistoryResponse> getMySharingGiven(Long memberId) {
        return sharingMapper.selectProfileSharingGiven(memberId);
    }

    @Override
    public List<SharingHistoryResponse> getMySharingReceived(Long memberId) {
        return sharingMapper.selectProfileSharingReceived(memberId);
    }



    @Override
    public ReviewInfoResponse getReviewInfo(Long sharingId, Long memberId) {

        SelectSharingDetailResponse sharing = sharingMapper.selectSharingDetail(sharingId, memberId);

        SharingScoreServiceImpl.ReviewerType type;

        if (memberId.equals(sharing.getMemberId())) {
            type = SharingScoreServiceImpl.ReviewerType.GIVER;
            ReviewInfoResponse query =
                    sharingMapper.selectReviewInfoForGiver(sharingId, memberId);
            if (query == null) {
                throw new IllegalStateException("후기 정보를 조회할 수 없습니다.");
            }
            return ReviewInfoResponse.builder()
                    .reviewerType(type)
                    .sharingId(query.getSharingId())
                    .partnerId(query.getPartnerId())
                    .partnerNickname(query.getPartnerNickname())
                    .title(query.getTitle())
                    .createdAt(query.getCreatedAt())
                    .build();

        } else if (memberId.equals(sharing.getTargetMemberId())) {
            type = SharingScoreServiceImpl.ReviewerType.RECEIVER;
            ReviewInfoResponse query =
                    sharingMapper.selectReviewInfoForReceiver(sharingId, memberId);
            if (query == null) {
                throw new IllegalStateException("후기 정보를 조회할 수 없습니다.");
            }

            return ReviewInfoResponse.builder()
                    .reviewerType(type)
                    .sharingId(query.getSharingId())
                    .partnerId(query.getPartnerId())
                    .partnerNickname(query.getPartnerNickname())
                    .title(query.getTitle())
                    .createdAt(query.getCreatedAt())
                    .build();

        } else {
            throw new IllegalArgumentException("후기 작성 권한이 없습니다.");
        }
    }



}
