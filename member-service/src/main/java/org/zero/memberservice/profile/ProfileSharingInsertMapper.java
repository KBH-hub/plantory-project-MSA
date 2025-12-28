package org.zero.memberservice.profile;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zero.memberservice.profile.dto.ProfileSharingResponse;

import java.util.List;

@Mapper
public interface ProfileSharingInsertMapper {
    List<ProfileSharingResponse> selectInterestSharingList(
            @Param("memberId") Long memberId,
            @Param("keyword") String keyword,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}

