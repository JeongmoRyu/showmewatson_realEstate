package com.watson.auth.user.domain.repository;

import com.watson.auth.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(String id);

    User findByAuthId(String authId);

    User findByNickname(String nickname);

}
