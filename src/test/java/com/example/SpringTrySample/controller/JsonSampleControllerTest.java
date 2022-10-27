package com.example.SpringTrySample.controller;

import com.example.SpringTrySample.entity.JsonSampleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class JsonSampleControllerTest {

    // Spring MVCのモック
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){

        // Spring MVCのモックを設定する
        this.mockMvc = MockMvcBuilders.standaloneSetup(new JsonSampleController()).build();
    }

    @Test
    void get() throws Exception{

        // GETで「jsonsample」にアクセスする
        mockMvc.perform(MockMvcRequestBuilders.get("/jsonsample"))
                .andExpect(status().isOk());
    }
}