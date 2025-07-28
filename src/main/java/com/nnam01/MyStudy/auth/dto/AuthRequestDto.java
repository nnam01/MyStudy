package com.nnam01.MyStudy.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDto {
  String email;
  String password;
}
