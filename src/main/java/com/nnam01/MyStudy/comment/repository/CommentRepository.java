package com.nnam01.MyStudy.repository;

import com.nnam01.MyStudy.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByPostId(Long postId);
}

