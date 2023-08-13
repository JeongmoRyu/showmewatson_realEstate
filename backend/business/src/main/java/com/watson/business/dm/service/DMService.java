package com.watson.business.dm.service;

import com.watson.business.dm.dto.dmrequest.ChatRoomExitRequest;
import com.watson.business.dm.dto.dmrequest.ChatRoomRequest;
import com.watson.business.dm.dto.dmrequest.DirectMessageRequest;
import com.watson.business.dm.dto.dmresponse.ChatRoomResponse;
import com.watson.business.dm.dto.dmresponse.DirectMessageResponse;

import java.util.List;

public interface DMService {
	/*
	* [채팅방]
	* 1. 채팅방 목록 조회
	* 2. 채팅방 만들기(메시지를 처음 보내는 경우)
	* 3. 채팅방 나가기(한쪽만 나가는 경우)
	* [메시지]
	* 1. 채팅방 내 메시지 목록 조회
	* 2. 메시지 보내기(처음 보내는 메시지일 경우, 채팅방부터 만들기)
	* */
	List<ChatRoomResponse> findChatRoomsByUserId(String userId);
	Long addChatRoom(ChatRoomRequest chatRoomRequest);
	Long modifyChatRoom(ChatRoomExitRequest chatRoomExitRequest, boolean isRealtor);

	List<DirectMessageResponse> findDirectMessagesByChatRoomId(Long chatRoomId);
	Long addDirectMessage(String senderId, DirectMessageRequest directMessageRequest);

}
