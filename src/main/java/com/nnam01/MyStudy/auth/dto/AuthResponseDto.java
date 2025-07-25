package com.nnam01.MyStudy.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AuthResponseDto {
  String access_token;
  String refresh_token;
}
