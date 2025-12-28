package org.zero.memberservice.profile;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zero.memberservice.profile.dto.ProfileInfoResponse;
import org.zero.memberservice.profile.dto.ProfileUpdateRequest;

@Mapper
public interface ProfileMapper {
    ProfileInfoResponse selectProfileInfo(Long memberId);

    int updateNoticeEnabled(@Param("memberId") Long memberId, @Param("enabled") int enabled);

    int updateProfileInfo(ProfileUpdateRequest request);

    int deleteMemberById(Long memberId);
}