package com.hello.hello.posts.service;

import com.hello.hello.posts.DataNotFoundException;
import com.hello.hello.posts.dto.PostRequestDTO;
import com.hello.hello.posts.dto.PostResponseDTO;
import com.hello.hello.posts.entity.Post;
import com.hello.hello.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //전체 글 조회
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOs = new ArrayList<>();

        for (Post post: posts) {
            PostResponseDTO postResponseDTO = PostResponseDTO.from(post);  //엔티티 -> dto로 변환
            postResponseDTOs.add(postResponseDTO);  //dto로 변환한 결과를 리스트에 추가
        }

        return postResponseDTOs;
    }


    //특정 글 상세 조회
    public PostResponseDTO getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            Post tempPost = post.get();
            PostResponseDTO postResponseDTO = PostResponseDTO.from(tempPost);
            return postResponseDTO;
        }
        else{
            throw new DataNotFoundException("post not found");
        }
    }


    //글 작성
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        Post post = Post.builder()
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .build();  //dto -> 엔티티

        postRepository.save(post);

        return PostResponseDTO.from(post);
    }

    //글 수정
    @Transactional
    public PostResponseDTO updatePost(Long id, PostRequestDTO postRequestDTO) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            Post tempPost = post.get();
            tempPost.update(postRequestDTO.getTitle(), postRequestDTO.getContent());
            return PostResponseDTO.from(tempPost);
        }
        else{
            throw new DataNotFoundException("post not found");
        }
    }


    //글 삭제
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("post not found"));

        postRepository.delete(post);
    }


}
