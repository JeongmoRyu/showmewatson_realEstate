package com.watson.auth.realtor.domain.repository;

import com.watson.auth.realtor.domain.entity.Realtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtorRepository extends JpaRepository<Realtor, Long> {
    Realtor findByAuthId(String authId);

    Realtor findById(String tmpId);

    Realtor findByLicense(String license);

    Realtor findByRegistId(String registId);

}
