package com.watson.business.wish.service;

import com.watson.business.wish.dto.WishRequest;

import java.util.List;

public interface WishService {
//		목록, 추가, 삭제
	List<Long> findWishedHouseIdByUserId(String userId);
	Long addWish(String userId, WishRequest wishRequest);
	Long modifyWish(String userId, WishRequest wishRequest);
}
