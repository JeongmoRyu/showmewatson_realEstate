package com.watson.business.house.dto.houserequest;

import lombok.*;

@Data
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseUpdateRequest {
	private String title;
	private String content;
//	가격 수정
	private Long salePrice;
	private Long deposit;
	private Long monthlyRent;
	private int maintenance;
	private String maintenanceList;
}
