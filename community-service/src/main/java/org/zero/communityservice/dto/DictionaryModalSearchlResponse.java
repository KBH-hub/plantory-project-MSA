package org.zero.communityservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zero.communityservice.global.plantoryEnum.ManageDemand;
import org.zero.communityservice.global.plantoryEnum.ManageLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictionaryModalSearchlResponse {
    private String plantName;

    private ManageLevel manageLevel;      // ENUM
    private String levelLabel;       // 라벨

    private ManageDemand manageDemand;       // ENUM
    private String demandLabel;      // 라벨

    private String fileUrl;
}
