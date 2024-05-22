package com.vimutti.controller;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    private final PostService postService;

    public UserController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostDTO post){
        //postId 를 response
        postService.write(post);
    }

    @GetMapping("/posts/{postId}")
    public PostEntity getOnePost(@PathVariable( name = "postId") Long postId){
        PostEntity postEntity = postService.getPost(postId);
        return postEntity;
    }
}
