package com.watson.business.livenotice.domain.repository;

import com.watson.business.livenotice.domain.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}