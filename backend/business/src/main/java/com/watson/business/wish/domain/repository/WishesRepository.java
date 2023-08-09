package com.watson.business.wish.domain.repository;

import com.watson.business.wish.domain.entity.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishesRepository extends JpaRepository<Wishes, Long> {
	@Query("SELECT w.houseId FROM Wishes w WHERE w.userId = :userId AND w.isDeleted = false")
	List<Long> findHouseIdByUserIdAndDeletedFalse(String userId);
}
