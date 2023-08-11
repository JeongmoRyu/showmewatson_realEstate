package com.watson.business.personalfilter.domain.repository;

import com.watson.business.personalfilter.domain.entity.PersonalFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalFilterRepository extends JpaRepository<PersonalFilter, Long> {
    List<PersonalFilter> findFiltersByUserId(String userId);
}
