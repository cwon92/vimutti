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
        PostDTO post = new PostDTO(1, "user1", "title1", "content1",
                "type1", null, null, 1);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(post);

        System.out.println(json);

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title1"))
                .andDo(print()); //서머리를 남기는 메서드

    }

    @Test
    @DisplayName("/posts 요청시 db에 저장")
    void postTest() throws Exception {
        //when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": \"user1\", " +
                                "\"title\": \"song\", \"content\": \"chiwon\", " +
                                "\"type\": \"normal\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("song"))
                //.andExpect(jsonPath("$.userId").value("user1"))
                //.andExpect(jsonPath("$.content").value("chiwon"))
                //.andExpect(jsonPath("$.type").value("normal"))
                //.andExpect(jsonPath("$.isPresented").value("1"))
                .andDo(print()); //서머리를 남기는 메서드

        //then
        assertEquals(1L, postRepository.count());

        //check
        PostEntity postEntity = postRepository.findAll().get(0);
        assertEquals("song", postEntity.getTitle());
        assertEquals("chiwon", postEntity.getContent());
    }
}