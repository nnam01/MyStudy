package com.nnam01.MyStudy.user.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String email;
  private String imageUrl;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
