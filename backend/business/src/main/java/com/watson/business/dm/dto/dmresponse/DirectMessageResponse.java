package com.watson.business.dm.dto.dmresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class DirectMessageResponse {
	private String sender;
	private String message;
	private LocalDateTime regDate;
}
