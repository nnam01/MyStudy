package com.nnam01.MyStudy.post.controller;

import com.nnam01.MyStudy.post.dto.PostDto;
import com.nnam01.MyStudy.post.dto.PostListDto;
import com.nnam01.MyStudy.post.dto.PostRequestDto;
import com.nnam01.MyStudy.post.service.PostService;
import com.nnam01.MyStudy.post.spec.PostApi;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController implements PostApi {
    private final PostService postService;

    @Override
    public ResponseEntity<Void> createPost(PostRequestDto postRequestDto) {
        PostDto post = postService.createPost(postRequestDto.getTitle(), postRequestDto.getContent(),
            postRequestDto.getAuthorId());
        return ResponseEntity.created(URI.create("/api/posts/"+post.getId())).build();
    }

    @Override
    public ResponseEntity<Void> updatePost(Long postId, PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletePost(Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PostDto> getPost(Long postId) {
        PostDto postDto = postService.getPostById(postId);
        if (postDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postDto);
    }

    @Override
    public ResponseEntity<PostListDto> getPostList() {
        return null;
    }
}
