package com.watson.business.dm.dto.dmrequest;

import lombok.Data;

@Data
public class DirectMessageRequest {
	private Long chatRoomId;
	private String message;
}
