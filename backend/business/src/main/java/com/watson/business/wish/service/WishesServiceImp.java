package com.watson.business.wish.service;

import com.watson.business.wish.domain.entity.Wishes;
import com.watson.business.wish.domain.repository.WishesRepository;
import com.watson.business.wish.dto.WishesRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WishesServiceImp implements WishesService {
	private final WishesRepository wishesRepository;

	@Override
	public List<Long> findWishedHouseIdByUserId(String userId) {
		return wishesRepository.findHouseIdByUserIdAndDeletedFalse(userId);
	}

	@Override
	public Long addWish(String userId, WishesRequest wishesRequest) {
		if (wishesRepository.findWishesByUserIdAndHouseId(userId, wishesRequest.getHouseId()) != null) {
			return modifyWish(userId, wishesRequest);
		}

		return wishesRepository.save(
				Wishes.builder()
				.houseId(wishesRequest.getHouseId())
				.userId(userId)
				.fcmToken(wishesRequest.getFcmToken())
				.build())
				.getId();
	}

	@Override
	public Long modifyWish(String userId, WishesRequest wishesRequest) {
		Wishes wish = wishesRepository.findWishesByUserIdAndHouseId(userId, wishesRequest.getHouseId());
		wish.editWish();
		return wish.getId();
	}
}
