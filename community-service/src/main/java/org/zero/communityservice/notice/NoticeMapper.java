package org.zero.communityservice.notice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zero.communityservice.notice.dto.NoticeDTO;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectNoticesByReceiver(@Param("receiverId") Long receiverId);
    int insertNotice(NoticeDTO noticeDTO);
    int updateNoticeReadFlag(Long noticeId);
    int deleteAllNotice(@Param("receiverId") Long receiverId);
    int existsTodayWateringNotice(@Param("receiverId") Long receiverId, @Param("targetId") Long targetId);
}
