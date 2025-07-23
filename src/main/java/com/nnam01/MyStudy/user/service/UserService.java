package com.nnam01.MyStudy.user.service;

import com.nnam01.MyStudy.user.domain.User;
import com.nnam01.MyStudy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(String username, String password, String email, String imageUrl) {
        User user = new User(username, password, email, imageUrl);
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userRepository.save(user);
    }

}
