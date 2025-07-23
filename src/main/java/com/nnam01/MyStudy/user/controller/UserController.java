package com.nnam01.MyStudy.user.controller;

import com.nnam01.MyStudy.user.domain.User;
import com.nnam01.MyStudy.user.dto.UserRequestDto;
import com.nnam01.MyStudy.user.service.UserService;
import com.nnam01.MyStudy.user.spec.UserApi;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<Void> createUser(UserRequestDto requestDto) {
        User user = userService.createUser(requestDto.getUsername(), requestDto.getPassword(),
            requestDto.getEmail(), requestDto.getImageUrl());
        return ResponseEntity.created(URI.create("/api/users/"+user.getId())).build();
    }
}
