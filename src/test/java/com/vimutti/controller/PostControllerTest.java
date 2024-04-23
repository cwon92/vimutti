package com.vimutti.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest //요걸 넣어야 NPE가 안뜸
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("/posts 요청시 hello world 출력")
    void test() throws Exception {
        //expected
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"))
                .andDo(print()); //서머리를 남기는 메서드

        System.out.println("sdfsd");
    }
}