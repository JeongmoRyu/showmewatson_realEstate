package com.watson.noticeproducer.live.domain.repository;

import com.watson.noticeproducer.live.domain.entity.NotificationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationInfo, String> {
}