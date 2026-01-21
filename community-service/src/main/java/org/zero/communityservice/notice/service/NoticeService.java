package org.zero.communityservice.notice.service;


import org.zero.communityservice.notice.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    List<NoticeDTO> getNoticeByReceiver(Long receiverId);
    int registerNotice(NoticeDTO request);
    int  updateNoticeReadFlag(Long noticeId);
    int removeAllNotice (Long receiverId);

}
