package com.vimutti.service;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.repository.PostRepository;
import com.vimutti.request.PostSearch;
import com.vimutti.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.DESC;


@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){ postRepository.deleteAll(); }


    @Test
    @DisplayName("글 작성")
    void test(){
        //given
        PostDTO postDTO = PostDTO.builder()
                .userId("user1")
                .title("title1")
                .content("content1")
                .type("type1")
                .build();

        //when
        postService.write(postDTO);

        //then
        assertEquals(1, postRepository.count());
        PostEntity postEntity = postRepository.findAll().get(0);
        assertEquals("title1", postEntity.getTitle());
        assertEquals("content1", postEntity.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2(){
        //given
        PostEntity req = PostEntity.builder()
                .userId("user1")
                .title("title1")
                .content("content1")
                .type("type1")
                .build();

        postRepository.save(req);

        //when
        PostResponse postResponse = postService.getPost(req.getPostId());

        //then
        assertNotNull(postResponse);
        assertEquals("title1", postResponse.getTitle());
        assertEquals("content1", postResponse.getContent());

    }

    @Test
    @DisplayName("글 1페이지 조회")
    void testGetList(){
        //given
        List<PostEntity> reqPosts = IntStream.range(0, 30)
                        .mapToObj(i -> {
                            return PostEntity.builder()
                                    .userId("user" + i)
                                    .title("title" + i)
                                    .content("content" + i)
                                    .type("type" + i)
                                    .build();
                        })
                        .collect(Collectors.toList());

        postRepository.saveAll(reqPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        //when
        List<PostResponse> posts = postService.getList(postSearch);

        //then
        assertNotNull(posts);
        assertEquals(10L, posts.size());
        assertEquals("title29", posts.get(0).getTitle());


    }

}