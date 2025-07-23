package com.nnam01.MyStudy.controller;

import com.nnam01.MyStudy.controller.spec.CommentApi;
import com.nnam01.MyStudy.domain.Comment;
import com.nnam01.MyStudy.dto.comment.CommentRequest;
import com.nnam01.MyStudy.dto.comment.CommentDto;
import com.nnam01.MyStudy.dto.comment.CommentListDto;
import com.nnam01.MyStudy.service.CommentService;
import java.util.List;
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
    public ResponseEntity<Void> createComment(Long postId, CommentRequest request) {
        commentService.createComment(postId, request.getAuthorId(), request.getContent());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CommentDto> getCommentById(Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @Override
    public ResponseEntity<CommentListDto> getCommentListByPostId(Long postId) {
        List<Comment> commentList = commentService.getCommentListByPostId(postId);
        return ResponseEntity.ok();
    }

    @Override
    public ResponseEntity<Void> updateComment(Long commentId, CommentRequest request) {
        commentService.updateComment(commentId, request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long commentId, CommentRequest request) {
        return null;
    }
}
