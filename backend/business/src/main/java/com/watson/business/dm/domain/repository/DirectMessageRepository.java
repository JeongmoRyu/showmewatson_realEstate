package com.watson.business.dm.domain.repository;

import com.watson.business.dm.domain.entity.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
	List<DirectMessage> findByChatRoomId(Long chatRoomId);
}
