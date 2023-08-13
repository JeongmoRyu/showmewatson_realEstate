package com.watson.business.dm.dto.dmresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ChatRoomResponse {
	private Long chatRoomId;
	private String realtorId;
	private String userNickname;
	private LocalDateTime lastDirectMessageRegDate;
}
