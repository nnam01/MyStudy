package com.nnam01.MyStudy.auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class RefreshToken {

  @Id
  private Long userId;

  @Column(nullable = false, length = 512)
  private String token;

  public RefreshToken(Long userId, String token) {
    this.userId = userId;
    this.token = token;
  }

  public void updateToken(String newToken) {
    this.token = newToken;
  }
}
