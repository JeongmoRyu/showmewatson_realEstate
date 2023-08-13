package com.watson.business.dm.dto.dmrequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomRequest {
	private String realtorId;
	private String userId;
	private Long houseId;
}
