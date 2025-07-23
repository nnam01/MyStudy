package com.nnam01.MyStudy.controller;

import com.nnam01.MyStudy.domain.Post;
import com.nnam01.MyStudy.dto.post.PostCreateRequest;
import com.nnam01.MyStudy.dto.post.PostUpdateRequest;
import com.nnam01.MyStudy.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest request) {
        Post post = postService.createPost(request.getTitle(), request.getContent(), request.getAuthorId());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        Post post = postService.updatePost(id, request);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
