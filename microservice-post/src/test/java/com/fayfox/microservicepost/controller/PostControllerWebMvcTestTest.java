package com.fayfox.microservicepost.controller;

import com.fayfox.microservicepost.dto.PostDTO;
import com.fayfox.microservicepost.mapper.CategoryMapper;
import com.fayfox.microservicepost.mapper.PostMapper;
import com.fayfox.microservicepost.pojo.Post;
import com.fayfox.microservicepost.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WebMvcTest，好像是需要人肉指定所有用到的Bean，并且模拟返回数据，还不太明白这个方式的用途
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class PostControllerWebMvcTestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService service;

    @MockBean
    private CategoryMapper categoryMapper;

    @MockBean
    private PostMapper postMapper;

    @Test
    public void getPostTest() throws Exception {
        PostDTO postDTO = new PostDTO();
        Post post = new Post();
        post.setId(10016);
        postDTO.setPost(post);
        when(service.get(10016)).thenReturn(postDTO);
        this.mockMvc.perform(get("/post/10016")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("10016")));
    }
}