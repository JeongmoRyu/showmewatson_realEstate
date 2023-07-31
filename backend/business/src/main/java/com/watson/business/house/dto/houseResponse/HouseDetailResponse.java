package com.watson.business.house.dto.houseResponse;

import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.HouseOption;
import com.watson.business.house.dto.item.STATUS;
import com.watson.business.region.dto.EmdResponse;
import lombok.*;

import java.util.Date;
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

    private String content;
    private List<HouseFile> houseFiles;
    private Date regDate;
    private HouseOption houseOption;
//    private boolean isWished;
}

