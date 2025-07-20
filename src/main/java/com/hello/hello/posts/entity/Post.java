package com.hello.hello.posts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor //기본생성자를 자동 생성
@AllArgsConstructor //모든 필드를 인자로 받는 생성자 생성
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post {

    //기본키 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    //글 제목
    private String title;

    //글 내용
    @Column(columnDefinition = "TEXT")
    private String content;

    //생성일
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    //수정일
    @LastModifiedDate
    private LocalDateTime updatedAt;


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
