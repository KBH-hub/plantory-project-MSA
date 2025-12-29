package org.zero.memberservice.profile.service;


import org.zero.memberservice.profile.dto.ProfileSharingHistoryListRequest;
import org.zero.memberservice.profile.dto.ProfileSharingHistoryListResponse;

import java.util.List;

public interface ProfileSharingHistoryService {

    int getInterestCount(Long memberId);

    int getCompletedSharingCount(Long memberId);

    List<ProfileSharingHistoryListResponse> getMySharingList(ProfileSharingHistoryListRequest request);

    List<ProfileSharingHistoryListResponse> getReceivedSharingList(ProfileSharingHistoryListRequest request);
}