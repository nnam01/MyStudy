package com.nnam01.MyStudy.auth.controller;

import com.nnam01.MyStudy.auth.dto.AuthRequestDto;
import com.nnam01.MyStudy.auth.dto.AuthResponseDto;
import com.nnam01.MyStudy.auth.dto.RefreshRequestDto;
import com.nnam01.MyStudy.auth.dto.RefreshResponseDto;
import com.nnam01.MyStudy.auth.repository.RefreshTokenRepository;
import com.nnam01.MyStudy.auth.service.AuthService;
import com.nnam01.MyStudy.auth.spec.AuthApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

  private final AuthService authService;
  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  public ResponseEntity<AuthResponseDto> login(AuthRequestDto authRequestDto) {
    return ResponseEntity.ok(authService.login(authRequestDto));
  }

  @Override
  public ResponseEntity<RefreshResponseDto> refresh(RefreshRequestDto requestDto) {
    return ResponseEntity.ok(authService.refresh(requestDto));
  }

  @Override
  public ResponseEntity<Void> logout(Long userId) {
    authService.logout(userId);
    return ResponseEntity.noContent().build();
  }
}
