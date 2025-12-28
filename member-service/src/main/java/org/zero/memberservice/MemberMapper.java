package org.zero.memberservice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    int countByMembername(@Param("membername") String membername);
    int countByNickname(@Param("nickname") String nickname);
}

