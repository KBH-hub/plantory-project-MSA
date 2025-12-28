package org.zero.communityservice.service;


import org.zero.communityservice.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    List<NoticeDTO> getNoticeByReceiver(Long receiverId);
    int registerNotice(NoticeDTO request);
    int  updateNoticeReadFlag(Long noticeId);
    int removeAllNotice (Long receiverId);

}
