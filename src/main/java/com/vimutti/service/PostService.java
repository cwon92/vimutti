package com.vimutti.service;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostEntity write(PostDTO postDTO){

        PostEntity postEntity = PostEntity.builder()
                .userId(postDTO.getUserId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .type(postDTO.getType())
                .build();

        return postRepository.save(postEntity);
    }

}
