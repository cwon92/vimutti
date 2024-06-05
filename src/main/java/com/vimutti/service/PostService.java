package com.vimutti.service;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.repository.PostRepository;
import com.vimutti.request.PostSearch;
import com.vimutti.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

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

    public PostResponse getPost(Long postId){
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글 ID입니다."));

        return PostResponse.builder()
                .postId(postEntity.getPostId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .build();


    }

    public List<PostResponse> getList(PostSearch postSearch) {

        //Pageable pageable = PageRequest.of(page, 10, Sort.by("postId").descending());

        return postRepository.getList(postSearch).stream()
                .map(post ->
                    PostResponse.builder()
                            .postId(post.getPostId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build()
                )
                .collect(Collectors.toList());
    }
}
