package com.nnam01.MyStudy.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {
    private String username;
    private String password;
    private String email;
    private String imageUrl;
}

