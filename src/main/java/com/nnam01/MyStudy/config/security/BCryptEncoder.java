package com.nnam01.MyStudy.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncoder {
  BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder(10);

  public String encodePassword(String rawPassword) {
    return bCryptEncoder.encode(rawPassword);
  }

  public boolean matches(String rawPassword, String encodedPassword) {
    return bCryptEncoder.matches(rawPassword, encodedPassword);
  }
}
