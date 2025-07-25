package com.nnam01.MyStudy.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private static final long ACCESS_TOKEN_EXPIRATION = 10 * 60 * 1000; // 10분
  private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7일

  private final JwtProperties jwtProperties;
  private Key secretKey;

  @PostConstruct
  public void init() {

    secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
  }

  public String generateAccessToken(Long id) {
    return generateToken(id, ACCESS_TOKEN_EXPIRATION, "access_token");
  }

  public String generateRefreshToken(Long id) {
    return generateToken(id, REFRESH_TOKEN_EXPIRATION, "refresh_token");
  }

  private String generateToken(Long id, long expirationTime, String subject) {
    Date date = new Date();
    return Jwts.builder()
        .header().type("JWT").and()// 헤더 파라미터 typ: JWT 명시 (기본값이지만 명시적으로 줄 수도 있음)
        .subject(subject) // sub: 토큰의 주제 또는 용도 명시 (보통 "access" or "refresh" 구분용)
        .issuer(jwtProperties.getIssuer()) // iss: 발급자 정보 (보통 서비스 이름)
        .audience().add("my_client").and() // aud: 대상자 (클라이언트 식별용)
        .id(UUID.randomUUID().toString()) // jti: JWT ID 고유 식별자 (중복 방지용)
        .issuedAt(date) // iat: 발급 시각
        .expiration(new Date(date.getTime()+expirationTime)) // exp: 만료 시각
        .notBefore(date) // nbf: 이 시간 이전엔 토큰 무효 (생략 가능)
        .claim("id", id) // 사용자 정의 클레임 - 사용자 ID
        .signWith(secretKey) // Key (+ 명시적 알고리즘)
        .compact(); // JWT 최종 문자열 생성
  }

  public boolean validateToken(String token){
    try {
      Jwts.parser()
          .verifyWith((SecretKey) secretKey)
          .build()
          .parseSignedClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  public Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public Long getUserIdFromToken(String token) {
    return getClaims(token).get("id", Long.class);
  }
}
