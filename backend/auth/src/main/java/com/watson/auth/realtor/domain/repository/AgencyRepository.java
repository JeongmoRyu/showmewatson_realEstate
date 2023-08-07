package com.watson.auth.realtor.domain.repository;

import com.watson.auth.realtor.domain.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency findByRegistId(String registId);
}
