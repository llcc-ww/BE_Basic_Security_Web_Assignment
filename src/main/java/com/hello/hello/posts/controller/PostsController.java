package com.hello.hello.posts.controller;

import com.hello.hello.posts.dto.PostRequestDTO;
import com.hello.hello.posts.dto.PostResponseDTO;
import com.hello.hello.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostsController {

    private final PostService postService;

    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostRequestDTO post) {
        return postService.createPost(post);
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostResponseDTO getPost(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PatchMapping("/{postId}")
    public PostResponseDTO updatePost(@PathVariable Long postId, @RequestBody PostRequestDTO postRequestDTO) {
        return postService.updatePost(postId, postRequestDTO);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }




}
