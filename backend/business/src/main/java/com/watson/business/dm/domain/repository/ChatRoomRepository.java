package com.watson.business.dm.domain.repository;

import com.watson.business.dm.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	List<ChatRoom> findChatRoomsByUserId(String userId);
}
