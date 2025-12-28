package org.zero.plantservice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zero.plantservice.dto.MyPlantRequest;

import java.util.List;

@Mapper
public interface MyPlantMapper {

    List<org.zero.plantservice.dto.MyPlantResponse> selectMyPlantList(@Param("memberId") Long memberId, @Param("name") String name, @Param("limit") int limit, @Param("offset") int offset);
    int insertMyPlant(org.zero.plantservice.dto.MyPlantRequest vo);
    int updateMyPlant(MyPlantRequest vo);
    int deletePlant(Long myplantId);
}
