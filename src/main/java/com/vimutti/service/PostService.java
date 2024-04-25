package com.vimutti.service;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostDTO postDTO){
        //postDto -> postEntity
        PostEntity postEntity =
                new PostEntity(postDTO.getUserId(), postDTO.getTitle(), postDTO.getContent(), postDTO.getType());

        postRepository.save(postEntity);
    }

}
