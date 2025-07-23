package com.nnam01.MyStudy.post.repository;

import com.nnam01.MyStudy.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

