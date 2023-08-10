package com.watson.business.personalfilter.service;

import com.watson.business.personalfilter.domain.entity.PersonalFilter;
import com.watson.business.personalfilter.domain.repository.PersonalFilterRepository;
import com.watson.business.personalfilter.dto.PersonalFilterRequest;
import com.watson.business.personalfilter.dto.PersonalFilterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonalFilterServiceImp implements PersonalFilterService {
    private final PersonalFilterRepository personalFilterRepository;
    @Override
    public List<PersonalFilterResponse> findPersonalFilterByUserId(String userId) {
        List<PersonalFilterResponse> responses = new ArrayList<>();
        List<PersonalFilter> filters = personalFilterRepository.findFiltersByUserId(userId);
        for(PersonalFilter filter : filters) {
            responses.add(filterEntityToDto(filter));
        }
        return responses;
    }

    @Override
    public Long addPersonalFilter(String userId, PersonalFilterRequest request) {
        log.debug("{}", request);
        return personalFilterRepository.save(filterDtoToEntity(userId, request)).getId();
    }

    @Override
    public Long modifyPersonalFilter(String userId, Long filterId, PersonalFilterRequest request) {
        try {
            personalFilterRepository.deleteById(filterId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 삭제된 필터 입니다.", e);        }
        return addPersonalFilter(userId, request);
    }

    private PersonalFilter filterDtoToEntity(String userId, PersonalFilterRequest request) {
        return PersonalFilter.builder()
                .userId(userId)
                .houseCode(request.getHouseCode())
                .contractCode(request.getContractCode())
                .courtCode(request.getCourtCode())
                .minSquareMeter(request.getMinSquareMeter())
                .maxSquareMeter(request.getMaxSquareMeter())
                .minDeposit(request.getMinDeposit())
                .maxDeposit(request.getMaxDeposit())
                .minMaintenance(request.getMinMaintenance())
                .maxMaintenance(request.getMaxMaintenance())
                .minMonthlyRent(request.getMinMonthlyRent())
                .maxMonthlyRent(request.getMaxMonthlyRent())
                .minSalePrice(request.getMinSalePrice())
                .maxSalePrice(request.getMaxSalePrice())
                .build();
    }
    private PersonalFilterResponse filterEntityToDto(PersonalFilter filter) {
        return PersonalFilterResponse.builder()
                .filterId(filter.getId())
                .houseCode(filter.getHouseCode())
                .contractCode(filter.getContractCode())
                .courtCode(filter.getCourtCode())
                .minSquareMeter(filter.getMinSquareMeter())
                .maxSquareMeter(filter.getMaxSquareMeter())
                .minDeposit(filter.getMinDeposit())
                .maxDeposit(filter.getMaxDeposit())
                .minMaintenance(filter.getMinMaintenance())
                .maxMaintenance(filter.getMaxMaintenance())
                .minMonthlyRent(filter.getMinMonthlyRent())
                .maxMonthlyRent(filter.getMaxMonthlyRent())
                .minSalePrice(filter.getMinSalePrice())
                .maxSalePrice(filter.getMaxSalePrice())
                .build();
    }
}
