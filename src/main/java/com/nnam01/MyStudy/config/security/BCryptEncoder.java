package com.nnam01.MyStudy.config.security;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class BCryptEncoder{
  BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder(5); //임시: 강도는 5로 설정
  public String encodePassword(String rawPassword) {
    return bCryptEncoder.encode(rawPassword);
  }

  public boolean matches(String rawPassword, String encodedPassword) {
    return bCryptEncoder.matches(rawPassword, encodedPassword);
  }
}
