package org.zero.memberservice.profile.service;


import org.zero.memberservice.profile.dto.ProfileInterestListRequest;
import org.zero.memberservice.profile.dto.ProfileSharingResponse;

import java.util.List;

public interface ProfileInterestService {
    List<ProfileSharingResponse> getProfileInterest(ProfileInterestListRequest request);
}
