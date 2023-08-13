package com.watson.business.dm.service;

import com.watson.business.dm.domain.entity.ChatRoom;
import com.watson.business.dm.domain.entity.DirectMessage;
import com.watson.business.dm.domain.repository.ChatRoomRepository;
import com.watson.business.dm.domain.repository.DirectMessageRepository;
import com.watson.business.dm.dto.dmrequest.ChatRoomExitRequest;
import com.watson.business.dm.dto.dmrequest.ChatRoomRequest;
import com.watson.business.dm.dto.dmrequest.DirectMessageRequest;
import com.watson.business.dm.dto.dmresponse.ChatRoomResponse;
import com.watson.business.dm.dto.dmresponse.DirectMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DMServiceImp implements DMService {
	private final ChatRoomRepository chatRoomRepository;
	private final DirectMessageRepository directMessageRepository;

	@Override
	public List<ChatRoomResponse> findChatRoomsByUserId(String userId) {
		List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsByUserId(userId);
		List<ChatRoomResponse>  responses = new ArrayList<>();
//		entity to dto(response)
		for(ChatRoom chatRoom : chatRooms) {
			ChatRoomResponse response = ChatRoomResponse.builder()
					.chatRoomId(chatRoom.getId())
//					.realtorId(chatRoom.get)
//					.userNickname()
//					.lastDirectMessageRegDate()
					.build();
			responses.add(response);
		}
		return responses;
	}

	@Override
	public Long addChatRoom(ChatRoomRequest chatRoomRequest) {
		return chatRoomRepository.save(
				ChatRoom.builder()
						.realtorId(chatRoomRequest.getRealtorId())
						.userId(chatRoomRequest.getUserId())
						.houseId(chatRoomRequest.getHouseId())
						.userDeleted(false).realtorDeleted(false)
						.build()
		).getId();
	}

	@Override
	public Long modifyChatRoom(ChatRoomExitRequest chatRoomExitRequest, boolean isRealtor) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomExitRequest.getChatRoomId()).orElseThrow(() -> new IllegalArgumentException("없는 채팅방입니다."));
		if(isRealtor)
			chatRoom.exitChatRoomRealtor();
		else
			chatRoom.exitChatRoomUser();
		return chatRoomExitRequest.getChatRoomId();
	}

	@Override
	public List<DirectMessageResponse> findDirectMessagesByChatRoomId(Long chatRoomId) {
		List<DirectMessage> directMessages = directMessageRepository.findByChatRoomId(chatRoomId);
		List<DirectMessageResponse> responses = new ArrayList<>();
//		entity to dto(dm repence)
		for(DirectMessage dm : directMessages) {
			DirectMessageResponse response = DirectMessageResponse.builder()
					.sender(dm.getSender())
					.message(dm.getMessage())
					.regDate(dm.getRegDate())
					.build();
			responses.add(response);
		}
		return responses;
	}

	@Override
	public Long addDirectMessage(String senderId, DirectMessageRequest directMessageRequest) {
		return directMessageRepository.save(
				DirectMessage.builder()
						.message(directMessageRequest.getMessage())
						.sender(senderId)
						.chatRoomId(directMessageRequest.getChatRoomId())
						.build()
		).getId();
	}
}
