package com.watson.business.dm.domain.entity;

import com.watson.business.house.domain.entity.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat_rooms")
public class ChatRoom extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "house_id")
	private Long houseId;

	@Column(name = "user_deleted")
	private boolean userDeleted;

	@Column(name = "realtor_deleted")
	private boolean realtorDeleted;

//	ManyToOne 공인중개사 연결
	@Column(name = "realtor_id")
	private String realtorId;

//	ManyToOne 일반사용자 연결
	@Column(name = "user_id")
	private String userId;

	public void exitChatRoomUser() {
		this.userDeleted = true;
	}
	public void exitChatRoomRealtor() {
		this.realtorDeleted = true;
	}
}
