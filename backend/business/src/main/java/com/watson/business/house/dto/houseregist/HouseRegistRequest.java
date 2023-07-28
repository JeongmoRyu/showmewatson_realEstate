package com.watson.business.house.dto.houseregist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HouseRegistRequest {
    /**
     * 1: 월세
     * 2: 전세
     * 3: 매매
     */
    private int contractCode;
    private int dongCode;

    /**
     * 1: 원룸
     * 2: 투룸
     * 3: 쓰리룸 이상
     */
    private int houseCode;
    private double squareMeter;
    private int floor;
    private int totalFloor;
    private String address;
    private String title;
    private String content;
    private double supplyAreaMeter;
    private String buildingUse;
    private String approvalBuildingDate;
    private int bathroom;
    private ContractInfoRequest contractInfo;
    private HouseOptionRequest houseOption;
    private List<MultipartFile> houseFiles;
}
