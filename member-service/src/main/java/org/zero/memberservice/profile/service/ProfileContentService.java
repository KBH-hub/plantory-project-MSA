package org.zero.memberservice.profile.service;


import org.zero.memberservice.profile.dto.ProfileWrittenDeleteRequest;
import org.zero.memberservice.profile.dto.ProfileWrittenListRequest;
import org.zero.memberservice.profile.dto.ProfileWrittenListResponse;

import java.util.List;

public interface ProfileContentService {

    List<ProfileWrittenListResponse> getProfileWrittenListAll(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> getProfileWrittenListSharing(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> getProfileWrittenListQuestion(ProfileWrittenListRequest request);

    boolean deleteProfileWrittenSharing(ProfileWrittenDeleteRequest request);

    boolean deleteProfileWrittenQuestion(ProfileWrittenDeleteRequest request);

    List<ProfileWrittenListResponse> searchProfileCommentAll(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> searchProfileCommentSharing(ProfileWrittenListRequest request);

    List<ProfileWrittenListResponse> searchProfileCommentQuestion(ProfileWrittenListRequest request);

    ProfileWrittenPageResult getProfileWrittenList(ProfileWrittenListRequest request, String category);

    void deleteWritten(ProfileWrittenDeleteRequest dto);
}
