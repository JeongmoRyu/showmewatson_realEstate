package com.watson.business.personalfilter.service;

import com.watson.business.personalfilter.dto.PersonalFilterRequest;
import com.watson.business.personalfilter.dto.PersonalFilterResponse;

import java.util.List;

public interface PersonalFilterService {
    List<PersonalFilterResponse> findPersonalFilterByUserId(String userId);
    Long addPersonalFilter(String userId, PersonalFilterRequest request);
    Long modifyPersonalFilter(String userId, Long filterId, PersonalFilterRequest request);
}
