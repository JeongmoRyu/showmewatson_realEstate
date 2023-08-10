package com.watson.business.wish.service;

import com.watson.business.wish.domain.repository.WishesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishesServiceImp implements WishesService {
	private final WishesRepository wishesRepository;
	@Override
	public List<Long> findWishesByUserid(String userId) {
		return wishesRepository.findHouseIdByUserIdAndDeletedFalse(userId);
	}
}
