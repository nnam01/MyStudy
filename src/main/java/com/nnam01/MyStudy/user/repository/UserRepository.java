package com.nnam01.MyStudy.user.repository;

import com.nnam01.MyStudy.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

  Optional<User> findById(Long id);
}
