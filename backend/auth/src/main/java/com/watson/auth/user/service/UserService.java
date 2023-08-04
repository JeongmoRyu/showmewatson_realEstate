package com.watson.auth.user.service;

import com.watson.auth.user.domain.entity.User;
import com.watson.auth.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByAuthId(String authId) {
        return userRepository.findByAuthId(authId);
    }

    public void modifyAccessToken(User user) {
        User findUser = userRepository.findByAuthId(user.getAuthId());
        findUser.setAccessToken(user.getAccessToken());
        userRepository.save(findUser);
    }

}