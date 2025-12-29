package org.zero.memberservice.profile.service;

import org.zero.memberservice.profile.dto.ProfileInfoResponse;
import org.zero.memberservice.profile.dto.ProfileUpdateRequest;
import org.zero.memberservice.profile.dto.PublicProfileResponse;

public interface ProfileService {

    ProfileInfoResponse getProfileInfo(Long memberId);

    boolean updateNoticeEnabled(Long memberId, int enabled);

    boolean updateProfileInfo(ProfileUpdateRequest request);

    boolean deleteMemberById(Long memberId);

    PublicProfileResponse getPublicProfile(Long memberId);

    boolean changePassword(Long memberId, String oldPassword, String newPassword);
}
