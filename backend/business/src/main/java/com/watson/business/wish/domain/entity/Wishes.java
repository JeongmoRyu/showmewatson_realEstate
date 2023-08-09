package com.watson.business.wish.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Wishes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "house_id")
	private Long houseId;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "fcm_token")
	private String fcmToken;

	public void editWish() {
		this.isDeleted = !isDeleted;
	}
}