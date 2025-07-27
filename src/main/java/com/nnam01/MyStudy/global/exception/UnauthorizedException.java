package com.nnam01.MyStudy.global.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
