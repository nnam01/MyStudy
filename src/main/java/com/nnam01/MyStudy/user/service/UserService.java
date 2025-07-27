package com.nnam01.MyStudy.user.service;

import com.nnam01.MyStudy.config.security.BCryptEncoder;
import com.nnam01.MyStudy.global.exception.UnauthorizedException;
import com.nnam01.MyStudy.user.domain.User;
import com.nnam01.MyStudy.user.dto.UserDto;
import com.nnam01.MyStudy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptEncoder bCryptEncoder;

    @Transactional
    public User createUser(String username, String password, String email, String imageUrl) {
        User user = new User(username, email, imageUrl);
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (email == null || email.isEmpty()|| !email.contains("@")) {
            throw new IllegalArgumentException("This is not a valid email address");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(bCryptEncoder.encodePassword(password));
        return userRepository.save(user);
    }

    public UserDto getMyInfo(Long userId) {
        if (userId == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserDto (user.getId(), user.getUsername(), user.getEmail(), user.getImageUrl(),
            user.getCreatedAt().toString(), user.getModifiedAt().toString());
    }
}
