package com.watson.business.wish.service;

import java.util.List;

public interface WishesService {
	List<Long> findWishesByUserid(String userId);
}
