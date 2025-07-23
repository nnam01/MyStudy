package com.nnam01.MyStudy.repository;

import com.nnam01.MyStudy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

