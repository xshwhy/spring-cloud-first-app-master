package com.fayfox.microservicepost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fayfox.microservicepost.pojo.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 直接调用方法测试，不走http，不过好像也不比走http快
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional //开启事务，自动回滚
public class PostControllerWebMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPostTest() throws Exception {
        this.mockMvc.perform(get("/post/10016")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("10016")));
    }

    @Test
    @Rollback(false) //有@Transactional就会自动回滚，不想回滚的话，加上@Rollback(false)
    public void createPostTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Post post = new Post();
        post.setCategoryId(10000);
        post.setTitle("单元测试");
        this.mockMvc.perform(
                post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(post)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }
}