package com.nnam01.MyStudy.service;

import com.nnam01.MyStudy.user.domain.User;
import com.nnam01.MyStudy.user.dto.UserUpdateRequest;
import com.nnam01.MyStudy.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String username, String password, String email, String imageUrl) {
        User user = new User(username, password, email, imageUrl);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setImageUrl(request.getImageUrl());
            user.setModifiedAt(java.time.LocalDateTime.now());
            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
