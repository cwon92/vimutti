package com.vimutti.controller;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
