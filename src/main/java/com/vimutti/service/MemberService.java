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
public class MemberService {

    private final PostRepository postRepository;

    public void write(PostDTO postDTO){

        PostEntity postEntity = PostEntity.builder()
                .userId(postDTO.getUserId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .type(postDTO.getType())
                .build();

        postRepository.save(postEntity);
    }

    public PostEntity getPost(Long postId){
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글 ID입니다."));

        return postEntity;
    }

}
