package com.vimutti.service;

import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


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
        PostEntity postEntity = postService.getPost(req.getPostId());

        //then
        assertNotNull(postEntity);
        assertEquals("title1", postEntity.getTitle());
        assertEquals("content1", postEntity.getContent());

    }

}