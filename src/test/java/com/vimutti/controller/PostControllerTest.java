package com.vimutti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimutti.domain.PostDTO;
import com.vimutti.domain.PostEntity;
import com.vimutti.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest //요걸 넣어야 NPE가 안뜸 (간단한 테스트용)
@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 title 필수")
    void test() throws Exception {
        //given
        PostDTO post = PostDTO.builder()
                .userId("user1")
                .title("title1")
                .content("content1")
                .type("type1")
                .build();

        String json = objectMapper.writeValueAsString(post);

        System.out.println(json);

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print()); //서머리를 남기는 메서드

    }

    @Test
    @DisplayName("/posts 요청시 db에 저장")
    void postTest() throws Exception {
        //given
        PostDTO post = PostDTO.builder()
                .userId("user1")
                .title("title1")
                .content("content1")
                .type("type1")
                .build();

        String json = objectMapper.writeValueAsString(post);
        //when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print()); //서머리를 남기는 메서드

        //then
        assertEquals(1L, postRepository.count());

        //check
        PostEntity postEntity = postRepository.findAll().get(0);
        assertEquals("title1", postEntity.getTitle());
        assertEquals("content1", postEntity.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void testGetPost() throws Exception {
        //given
        PostEntity postEntity = PostEntity.builder()
                .userId("user1")
                .title("title1")
                .content("content1")
                .type("type1")
                .build();

        PostEntity post = postRepository.save(postEntity);

        //expected
        mockMvc.perform(get("/posts/{postId}", post.getPostId())
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.userId").value(post.getUserId()))
                .andExpect(jsonPath("$.content").value(post.getContent()))
                .andDo(print());

        //then
    }

}