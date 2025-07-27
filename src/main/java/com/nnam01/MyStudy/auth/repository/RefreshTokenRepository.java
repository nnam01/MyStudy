package com.nnam01.MyStudy.auth.repository;

import com.nnam01.MyStudy.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

}
