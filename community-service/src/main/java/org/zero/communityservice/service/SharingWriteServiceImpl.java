package org.zero.communityservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zero.communityservice.ImageMapper;
import org.zero.communityservice.NoticeMapper;
import org.zero.communityservice.SharingMapper;
import org.zero.communityservice.dto.*;
import org.zero.communityservice.global.plantoryEnum.ImageTargetType;
import org.zero.communityservice.global.plantoryEnum.NoticeTargetType;
import org.zero.communityservice.global.utils.StorageUploader;
import org.zero.communityservice.repository.Sharing;
import org.zero.communityservice.repository.SharingRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SharingWriteServiceImpl implements SharingWriteService {

    private final SharingMapper sharingMapper;
    private final ImageMapper imageMapper;
    private final StorageUploader storageUploader;
    private final NoticeMapper noticeMapper;

    private final SharingRepository sharingRepository;

    @Override
    @Transactional
    public Long registerSharing(SharingRequest request, List<MultipartFile> files) throws IOException {

        List<ImageDTO> imageList = new ArrayList<>();

        if (request.getTitle() == null || request.getTitle().isBlank())
            throw new IllegalArgumentException("제목은 필수입니다.");

        if (request.getContent() == null || request.getContent().isBlank())
            throw new IllegalArgumentException("내용은 필수입니다.");

        if (files != null) {
            for (MultipartFile file : files) {

                if (file.isEmpty()) continue;

                String url = storageUploader.uploadFile(file);

                imageList.add(ImageDTO.builder()
                        .memberId(request.getMemberId())
                        .targetType(ImageTargetType.SHARING)
                        .fileUrl(url)
                        .fileName(file.getOriginalFilename())
                        .build());
            }
        }

//        sharingMapper.insertSharing(request);
//        Long sharingId = request.getSharingId();
        Sharing sharing = new Sharing(
                request.getMemberId(),
                request.getTitle(),
                request.getContent(),
                request.getPlantType(),
                request.getManagementLevel() != null ? request.getManagementLevel().name() : null,
                request.getManagementNeeds() != null ? request.getManagementNeeds().name() : null
        );

        sharingRepository.save(sharing);
        Long sharingId = sharing.getId();


        for (ImageDTO img : imageList) {
            img.setTargetId(sharingId);
            imageMapper.insertImage(img);
        }

        return sharingId;
    }


    @Override
    @Transactional
    public boolean updateSharing(SharingRequest request, List<MultipartFile> newImages) throws IOException {

        Sharing sharing = sharingRepository
                .findByIdAndDelFlagIsNull(request.getSharingId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 나눔글"));

        if (!sharing.getMemberId().equals(request.getMemberId())) {
            throw new IllegalStateException("본인 글만 수정 가능");
        }

        sharing.update(
                request.getTitle(),
                request.getContent(),
                request.getManagementLevel() != null ? request.getManagementLevel().name() : null,
                request.getManagementNeeds() != null ? request.getManagementNeeds().name() : null
        );

        Long sharingId = sharing.getId();

        if (request.getDeletedImageIdList() != null) {
            for (Long id : request.getDeletedImageIdList()) {
                imageMapper.softDeleteImage(id);
            }
        }

        if (newImages != null) {
            for (MultipartFile file : newImages) {
                if (file.isEmpty()) continue;

                String url = storageUploader.uploadFile(file);

                imageMapper.insertImage(ImageDTO.builder()
                        .memberId(request.getMemberId())
                        .targetType(ImageTargetType.SHARING)
                        .targetId(sharingId)
                        .fileUrl(url)
                        .fileName(file.getOriginalFilename())
                        .build());
            }
        }

        return true;
    }



    @Override
    @Transactional
    public boolean deleteSharing(Long sharingId, Long memberId) {

        if (!sharingRepository.existsByIdAndMemberIdAndDelFlagIsNull(sharingId, memberId)) {
            throw new IllegalStateException("본인 글만 삭제 가능");
        }

        sharingRepository.softDelete(sharingId);
        imageMapper.softDeleteImagesByTarget(ImageTargetType.SHARING, sharingId);

        return true;
    }


    @Override
    @Transactional
    public boolean addInterest(Long memberId, Long sharingId) {

        int exists = sharingMapper.countInterest(memberId, sharingId);
        if (exists > 0) return false;

        sharingMapper.insertInterest(memberId, sharingId);
        sharingMapper.increaseInterestNum(sharingId);

        return true;
    }

    @Override
    @Transactional
    public boolean removeInterest(Long memberId, Long sharingId) {

        int exists = sharingMapper.countInterest(memberId, sharingId);
        if (exists == 0) return false;

        sharingMapper.deleteInterest(memberId, sharingId);
        sharingMapper.decreaseInterestNum(sharingId);

        return true;
    }

    @Override
    @Transactional
    public boolean addComment(CommentRequest request) {

        Long sharingId = request.getSharingId();
        Long writerId = request.getWriterId();
        String content = request.getContent();

        SelectSharingDetailResponse sharing = sharingMapper.selectSharingDetail(sharingId, null);
        if (sharing == null) {
            throw new IllegalArgumentException("존재하지 않는 나눔글입니다.");
        }

        int inserted = sharingMapper.insertComment(sharingId, writerId, content);

        if (inserted > 0) {

            Long ownerId = sharing.getMemberId();

            if (!writerId.equals(ownerId)) {

                NoticeDTO notice = NoticeDTO.builder()
                        .receiverId(ownerId)
                        .targetId(sharingId)
                        .targetType(NoticeTargetType.SHARING)
                        .content("새 댓글 알림 | 제목: " + sharing.getTitle())
                        .build();

                noticeMapper.insertNotice(notice);
            }
        }

        return inserted > 0;
    }


    @Override
    @Transactional
    public boolean updateComment(CommentRequest request) {

        int isMine = sharingMapper.countProfileComment(request.getCommentId(), request.getSharingId(), request.getWriterId());
        if (isMine == 0) {
            throw new IllegalStateException("본인 댓글만 수정 가능");
        }

        return sharingMapper.updateCommentById(request) > 0;
    }

    @Override
    @Transactional
    public boolean deleteComment(Long commentId, Long sharingId, Long writerId) {

        int isMine = sharingMapper.countProfileComment(commentId, sharingId, writerId);
        if (isMine == 0) {
            throw new IllegalStateException("본인 댓글만 삭제 가능");
        }

        return sharingMapper.deleteComment(commentId, sharingId, writerId) > 0;
    }



}
