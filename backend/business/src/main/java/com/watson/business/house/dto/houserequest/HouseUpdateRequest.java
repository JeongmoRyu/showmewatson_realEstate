package com.watson.business.house.dto.houserequest;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseUpdateRequest {
	private String title;
	private String content;
}
