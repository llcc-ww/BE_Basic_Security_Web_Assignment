package com.hello.hello.posts.dto;

import com.hello.hello.posts.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor //기본생성자를 자동 생성
@AllArgsConstructor
@Getter
public class PostResponseDTO {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponseDTO from(Post post) {
        return new PostResponseDTO(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

}
