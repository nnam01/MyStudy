package com.nnam01.MyStudy.post.service;

import com.nnam01.MyStudy.post.domain.Post;
import com.nnam01.MyStudy.post.dto.PostDto;
import com.nnam01.MyStudy.post.dto.PostListDto;
import com.nnam01.MyStudy.post.dto.PostRequestDto;
import com.nnam01.MyStudy.post.mapper.PostDtoMapper;
import com.nnam01.MyStudy.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostDtoMapper postDtoMapper;

    @Transactional
    public PostDto createPost(String title, String content, Long authorId) {
        Post post = postRepository.save(new Post(title, content, authorId));
        return postDtoMapper.toDto(post);
    }

    @Transactional(readOnly = true)
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        return postDtoMapper.toDto(post);
    }

    @Transactional
    public PostDto updatePost(Long id, PostRequestDto request) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setModifiedAt(LocalDateTime.now());
        return postDtoMapper.toDto(postRepository.save(post));

    }

    @Transactional
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }


    public PostListDto getPostList() {
        //임시: 아직 미구현
        return new PostListDto( Collections.emptyList());
    }
}
