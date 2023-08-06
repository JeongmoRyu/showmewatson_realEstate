package com.watson.business.house.dto.houseresponse;

import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.region.dto.EmdResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDetailResponse {

    private HouseListResponse houseListResponse;

    private RealtorResponse realtor;
    private String maintenanceList;
    private int contractCode;
    private EmdResponse emdResponse;
    private int totalFloor;
    private String buildingUse;
    private String approvalBuildingDate;
    private int bathroom;
    private int room;

    private String content;
    private List<HouseFile> houseFiles;
    private LocalDateTime regDate;
    private HouseOption houseOption;
}

