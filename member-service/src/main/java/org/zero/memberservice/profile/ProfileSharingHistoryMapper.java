package org.zero.memberservice.profile;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zero.memberservice.profile.dto.ProfileSharingHistoryListRequest;
import org.zero.memberservice.profile.dto.ProfileSharingHistoryListResponse;

import java.util.List;

@Mapper
public interface ProfileSharingHistoryMapper {
    int countByInterestCount(@Param("memberId") Long memberId);

    int countByCompletedSharingCount(@Param("memberId") Long memberId);

    List<ProfileSharingHistoryListResponse> selectProfileSharingList(ProfileSharingHistoryListRequest request);

    List<ProfileSharingHistoryListResponse> selectMySharingList(ProfileSharingHistoryListRequest request);

    List<ProfileSharingHistoryListResponse> selectReceivedSharingList(ProfileSharingHistoryListRequest request);

}

