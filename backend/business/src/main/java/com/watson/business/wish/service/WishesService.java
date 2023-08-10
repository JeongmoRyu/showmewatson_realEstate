package com.watson.business.wish.service;

import com.watson.business.wish.dto.WishesRequest;

import java.util.List;

public interface WishesService {
//		목록, 추가, 삭제
	List<Long> findWishedHouseIdByUserId(String userId);
	Long addWish(String userId, WishesRequest wishesRequest);
	Long modifyWish(String userId, WishesRequest wishesRequest);
}
