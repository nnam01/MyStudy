package com.nnam01.MyStudy.user.repository;

import com.nnam01.MyStudy.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);
}

