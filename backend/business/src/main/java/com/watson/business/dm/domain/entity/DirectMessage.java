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
@Table(name = "direct_messages")
public class DirectMessage extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message")
	private String message;

	@Column(name = "sender")
	private String sender;

//	채팅방 id : 채팅방 연결?
	@Column(name = "chat_room_id")
	private Long chatRoomId;
}
