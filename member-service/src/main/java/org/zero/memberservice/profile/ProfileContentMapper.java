package org.zero.memberservice.profile;

import org.apache.ibatis.annotations.Mapper;
import org.zero.memberservice.profile.dto.ProfileWrittenDeleteRequest;
import org.zero.memberservice.profile.dto.ProfileWrittenListRequest;
import org.zero.memberservice.profile.dto.ProfileWrittenListResponse;

import java.util.List;

@Mapper
public interface ProfileContentMapper {
    List<ProfileWrittenListResponse> selectProfileWrittenListAll(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> selectProfileWrittenListSharing(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> selectProfileWrittenListQuestion(ProfileWrittenListRequest request);

    int countProfileWrittenAll(ProfileWrittenListRequest request);

    int countProfileCommentAll(ProfileWrittenListRequest request);

    int deleteProfileWrittenSharing(ProfileWrittenDeleteRequest request);

    int deleteProfileWrittenQuestion(ProfileWrittenDeleteRequest request);

    List<ProfileWrittenListResponse> selectProfileCommentSearchAll(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> selectProfileCommentSearchSharing(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> selectProfileCommentSearchQuestion(ProfileWrittenListRequest request);

    int countProfileWrittenQuestion(ProfileWrittenListRequest req);

    int countProfileWrittenSharing(ProfileWrittenListRequest req);

    int countProfileCommentSharing(ProfileWrittenListRequest req);

    int countProfileCommentQuestion(ProfileWrittenListRequest req);
}
