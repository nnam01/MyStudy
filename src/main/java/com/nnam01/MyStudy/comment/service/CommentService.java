package com.nnam01.MyStudy.comment.service;

import com.nnam01.MyStudy.comment.domain.Comment;
import com.nnam01.MyStudy.comment.dto.CommentDto;
import com.nnam01.MyStudy.comment.dto.CommentListDto;
import com.nnam01.MyStudy.comment.dto.CommentRequestDto;
import com.nnam01.MyStudy.comment.mapper.CommentDtoMapper;
import com.nnam01.MyStudy.comment.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDtoMapper commentDtoMapper;

    public Comment createComment(Long postId, Long authorId, String content) {
        return commentRepository.save(new Comment(postId, authorId, content));
    }

    @Transactional
    public void updateComment(Long id, CommentRequestDto request) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
        comment.setAuthorId(request.getAuthorId());
        comment.setContent(request.getContent());
        comment.setModifiedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public CommentListDto getCommentListByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No comments found for post ID: " + postId);
        }
        return commentDtoMapper.toCommentListDto(comments);
    }

    public CommentDto getCommentById(Long commentId) {
        Comment comment =commentRepository.findById(commentId)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return commentDtoMapper.toDto(comment);
    }
}
