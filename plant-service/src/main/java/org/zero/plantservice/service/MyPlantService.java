package org.zero.plantservice.service;

import org.springframework.web.multipart.MultipartFile;
import org.zero.plantservice.dto.MyPlantRequest;
import org.zero.plantservice.dto.MyPlantResponse;

import java.io.IOException;
import java.util.List;

public interface MyPlantService {
    List<MyPlantResponse> getMyPlantList(Long memberId, String name , int limit, int offset);
    int registerMyPlant(MyPlantRequest vo, MultipartFile file, Long memberId) throws IOException;
    int updateMyPlant(MyPlantRequest vo, Long delFile, MultipartFile file, Long memberId) throws IOException;
    int removePlant(Long myplantId, Long delFile) throws IOException;
}
