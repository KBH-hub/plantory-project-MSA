package org.zero.plantservice.service;

import org.springframework.web.multipart.MultipartFile;
import org.zero.plantservice.dto.ImageDTO;
import org.zero.plantservice.global.plantoryEnum.ImageTargetType;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    String uploadProfileImage(Long memberId, MultipartFile file) throws IOException;
    ImageDTO getProfileImage(Long memberId);
    String getProfileImageUrl(Long memberId);
    List<ImageDTO> getImagesByTarget(ImageTargetType targetType, Long targetId);
}
