package org.zero.communityservice.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zero.communityservice.notice.NoticeMapper;
import org.zero.communityservice.notice.dto.NoticeDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;


    @Override
    public List<NoticeDTO> getNoticeByReceiver(Long receiverId) {
        return noticeMapper.selectNoticesByReceiver(receiverId);
    }

    @Override
    public int registerNotice(NoticeDTO request) {
        return noticeMapper.insertNotice(request);
    }

    @Override
    public int updateNoticeReadFlag(Long noticeId) {
        return noticeMapper.updateNoticeReadFlag(noticeId);
    }

    @Override
    public int removeAllNotice(Long receiverId) {
        return noticeMapper.deleteAllNotice(receiverId);
    }
}
