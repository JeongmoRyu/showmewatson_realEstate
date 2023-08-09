package com.watson.business.wish.service;

import com.watson.business.wish.domain.entity.Wish;
import com.watson.business.wish.domain.repository.WishRepository;
import com.watson.business.wish.dto.WishRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WishServiceImp implements WishService {
	private final WishRepository wishRepository;

	@Override
	public List<Long> findWishedHouseIdByUserId(String userId) {
		return wishRepository.findHouseIdByUserIdAndDeletedFalse(userId);
	}

	@Override
	public Long addWish(String userId, WishRequest wishRequest) {
		if (wishRepository.findWishesByUserIdAndHouseId(userId, wishRequest.getHouseId()) != null) {
			return modifyWish(userId, wishRequest);
		}

		return wishRepository.save(
				Wish.builder()
				.houseId(wishRequest.getHouseId())
				.userId(userId)
				.fcmToken(wishRequest.getFcmToken())
				.build())
				.getId();
	}

	@Override
	public Long modifyWish(String userId, WishRequest wishRequest) {
		Wish wish = wishRepository.findWishesByUserIdAndHouseId(userId, wishRequest.getHouseId());
		wish.editWish();
		return wish.getId();
	}
}
