package com.vimutti.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponse {

    private Long postId;
    private String userId;
    private String title;
    private String content;



}
