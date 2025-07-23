package com.nnam01.MyStudy.repository;

import com.nnam01.MyStudy.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

