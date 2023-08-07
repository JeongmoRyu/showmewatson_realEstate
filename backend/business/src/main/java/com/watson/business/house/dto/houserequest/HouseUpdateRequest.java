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
}
