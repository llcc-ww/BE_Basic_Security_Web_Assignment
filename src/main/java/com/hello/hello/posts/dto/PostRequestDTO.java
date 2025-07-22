package com.hello.hello.posts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본생성자를 자동 생성
@Getter
public class PostRequestDTO {
    private String title;
    private String content;

}
