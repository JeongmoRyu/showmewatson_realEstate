package com.watson.business.dm.controller;

import com.watson.business.dm.dto.dmrequest.ChatRoomExitRequest;
import com.watson.business.dm.dto.dmrequest.ChatRoomRequest;
import com.watson.business.dm.dto.dmrequest.DirectMessageRequest;
import com.watson.business.dm.dto.dmresponse.ChatRoomResponse;
import com.watson.business.dm.dto.dmresponse.DirectMessageResponse;
import com.watson.business.dm.service.DMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
@RequiredArgsConstructor
public class DMController {
	private final DMService dmService;

	private String userId = "admin";
	private boolean isRealtor = false;

	@GetMapping("")     // 채팅방 목록 보기
	public ResponseEntity<List<ChatRoomResponse>> chatRoomList() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(dmService.findChatRoomsByUserId(userId));
	}
	@PostMapping("")
	public ResponseEntity<String> chatRoomAdd(@RequestBody ChatRoomRequest chatRoomRequest) {      // 채팅방 만들기
		dmService.addChatRoom(chatRoomRequest);
		return ResponseEntity.status(HttpStatus.OK).body("채팅방이 만들어졌습니다.");
	}
	@PutMapping("")
	public ResponseEntity<String> chatRoomRemove(@RequestBody ChatRoomExitRequest chatRoomExitRequest) {       // 채팅방 나가기
		dmService.modifyChatRoom(chatRoomExitRequest, isRealtor);
		return ResponseEntity.status(HttpStatus.OK)
				.body("채팅방을 나갔습니다.");
	}
	@GetMapping("/{chat_room_id}")
	public ResponseEntity<List<DirectMessageResponse>> directMessageList(@PathVariable("chat_room_id") Long chatRoomId) {// 메시지 목록 보기
		return ResponseEntity.status(HttpStatus.OK)
				.body(dmService.findDirectMessagesByChatRoomId(chatRoomId));
	}
	@PostMapping("/{chat_room_id}")
	public ResponseEntity<String> directMessageAdd(@PathVariable("chat_room_id") Long chatRoomId,
	                                          @RequestBody DirectMessageRequest directMessageRequest) {      // 메시지 보내기
		dmService.addDirectMessage(userId, directMessageRequest);
		return ResponseEntity.status(HttpStatus.OK).body("메시지가 보내졌습니다.");
	}
}
