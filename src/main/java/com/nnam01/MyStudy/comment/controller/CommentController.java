package com.nnam01.MyStudy.comment.controller;

import com.nnam01.MyStudy.comment.domain.Comment;
import com.nnam01.MyStudy.comment.dto.CommentDto;
import com.nnam01.MyStudy.comment.dto.CommentListDto;
import com.nnam01.MyStudy.comment.dto.CommentRequestDto;
import com.nnam01.MyStudy.comment.service.CommentService;
import com.nnam01.MyStudy.comment.spec.CommentApi;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController implements CommentApi {
  private final CommentService commentService;

  @Override
  public ResponseEntity<Void> createComment(Long postId, CommentRequestDto request) {
    Comment comment =
        commentService.createComment(postId, request.getAuthorId(), request.getContent());
    return ResponseEntity.created(URI.create("/api/comments/" + comment.getId())).build();
  }

  @Override
  public ResponseEntity<CommentDto> getCommentById(Long commentId) {
    return ResponseEntity.ok(commentService.getCommentById(commentId));
  }

  @Override
  public ResponseEntity<CommentListDto> getCommentListByPostId(Long postId) {
    return ResponseEntity.ok(commentService.getCommentListByPostId(postId));
  }

  @Override
  public ResponseEntity<Void> updateComment(Long commentId, CommentRequestDto request) {
    commentService.updateComment(commentId, request);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteComment(Long commentId) {
    commentService.deleteComment(commentId);
    return ResponseEntity.noContent().build();
  }
}
