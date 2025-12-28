package org.zero.memberservice.image.service;

import org.springframework.web.multipart.MultipartFile;
import org.zero.memberservice.global.plantoryEnum.ImageTargetType;
import org.zero.memberservice.image.dto.ImageDTO;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    String uploadProfileImage(Long memberId, MultipartFile file) throws IOException;
    ImageDTO getProfileImage(Long memberId);
    String getProfileImageUrl(Long memberId);
    List<ImageDTO> getImagesByTarget(ImageTargetType targetType, Long targetId);
}
