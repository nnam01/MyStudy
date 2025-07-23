package com.nnam01.MyStudy.service;

import com.nnam01.MyStudy.comment.domain.Comment;
import com.nnam01.MyStudy.comment.dto.CommentRequest;
import com.nnam01.MyStudy.comment.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Long postId, Long authorId, String content) {
        Comment comment = new Comment(postId, authorId, content);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setPostId(request.getPostId());
            comment.setAuthorId(request.getAuthorId());
            comment.setContent(request.getContent());
            comment.setModifiedAt(java.time.LocalDateTime.now());
            return commentRepository.save(comment);
        }
        return null;
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentListByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No comments found for post ID: " + postId);
        }
        return comments;
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }
}
