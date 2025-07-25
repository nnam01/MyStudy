package com.nnam01.MyStudy.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String email;
  private String imageUrl;
  private String createdAt;
  private String modifiedAt;
}
