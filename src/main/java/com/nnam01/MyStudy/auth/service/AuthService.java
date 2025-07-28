package com.nnam01.MyStudy.auth.service;

import com.nnam01.MyStudy.auth.domain.RefreshToken;
import com.nnam01.MyStudy.auth.dto.AuthRequestDto;
import com.nnam01.MyStudy.auth.dto.AuthResponseDto;
import com.nnam01.MyStudy.auth.dto.RefreshRequestDto;
import com.nnam01.MyStudy.auth.dto.RefreshResponseDto;
import com.nnam01.MyStudy.auth.repository.RefreshTokenRepository;
import com.nnam01.MyStudy.config.security.BCryptEncoder;
import com.nnam01.MyStudy.config.security.jwt.TokenProvider;
import com.nnam01.MyStudy.global.exception.UnauthorizedException;
import com.nnam01.MyStudy.user.domain.User;
import com.nnam01.MyStudy.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final BCryptEncoder bCryptEncoder;
  private final TokenProvider tokenProvider;

  public AuthResponseDto login(AuthRequestDto authRequestDto) {
    User user =
        userRepository
            .findByEmail(authRequestDto.getEmail())
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

    if (!bCryptEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
      throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
    }
    String accessToken = tokenProvider.generateAccessToken(user.getId());
    String refreshToken = tokenProvider.generateRefreshToken(user.getId());
    RefreshToken saveToken = new RefreshToken(user.getId(), refreshToken);
    refreshTokenRepository.save(saveToken);
    return new AuthResponseDto(accessToken, refreshToken);
  }

  public RefreshResponseDto refresh(RefreshRequestDto requestDto) {
    String refreshToken = requestDto.getRefreshToken();
    tokenProvider.validateToken(refreshToken);

    Long userId = tokenProvider.getUserIdFromToken(refreshToken);

    RefreshToken saved =
        refreshTokenRepository
            .findById(userId)
            .orElseThrow(() -> new UnauthorizedException("존재하지 않는 리프레쉬 토큰입니다."));

    if (!saved.getToken().equals(refreshToken)) {
      throw new UnauthorizedException("리프레쉬 토큰이 일치하지 않습니다.");
    }
    String newAccessToken = tokenProvider.generateAccessToken(userId);
    return new RefreshResponseDto(newAccessToken);
  }

  public void logout(Long userId) {
    if (!refreshTokenRepository.existsById(userId)) {
      throw new UnauthorizedException("로그아웃할 사용자가 존재하지 않습니다.");
    }
    refreshTokenRepository.deleteById(userId);
  }
}
