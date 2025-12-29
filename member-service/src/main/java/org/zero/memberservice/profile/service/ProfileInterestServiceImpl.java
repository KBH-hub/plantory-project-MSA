package org.zero.memberservice.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zero.memberservice.global.plantoryEnum.ImageTargetType;
import org.zero.memberservice.image.ImageMapper;
import org.zero.memberservice.profile.ProfileSharingInsertMapper;
import org.zero.memberservice.profile.dto.ProfileInterestListRequest;
import org.zero.memberservice.profile.dto.ProfileSharingResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileInterestServiceImpl implements ProfileInterestService {

    private final ProfileSharingInsertMapper profileSharingInsertMapper;
    private final ImageMapper imageMapper;

    @Override
    public List<ProfileSharingResponse> getProfileInterest(ProfileInterestListRequest req) {

        List<ProfileSharingResponse> list =
                profileSharingInsertMapper.selectInterestSharingList(
                        req.getMemberId(),
                        req.getKeyword(),
                        req.getLimit(),
                        req.getOffset()
                );

        list.forEach(item -> {
            String thumbnail = imageMapper.selectSharingThumbnail(
                    ImageTargetType.SHARING,
                    item.getSharingId()
            );
            item.setThumbnail(thumbnail);
        });

        return list;
    }
}
