package com.nnam01.MyStudy.service;

import com.nnam01.MyStudy.post.domain.Post;
import com.nnam01.MyStudy.post.dto.PostUpdateRequest;
import com.nnam01.MyStudy.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(String title, String content, Long authorId) {
        Post post = new Post(title, content, authorId);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public java.util.List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(request.getTitle());
            post.setContent(request.getContent());
            post.setAuthorId(request.getAuthorId());
            post.setModifiedAt(java.time.LocalDateTime.now());
            return postRepository.save(post);
        }
        return null;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
