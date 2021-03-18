package com.fayfox.microservicepost.service;

import com.fayfox.dto.RespDTO;
import com.fayfox.microservicepost.client.UserServiceClient;
import com.fayfox.microservicepost.dto.PostDTO;
import com.fayfox.microservicepost.mapper.PostMapper;
import com.fayfox.microservicepost.pojo.Post;
import com.fayfox.microservicepost.pojo.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PostService {
    private PostMapper postMapper;

    private UserServiceClient userServiceClient;

    public PostService(PostMapper postMapper, UserServiceClient userServiceClient) {
        this.postMapper = postMapper;
        this.userServiceClient = userServiceClient;
    }

    public PostDTO get(int id) {
        Post post = postMapper.findById(id);
        RespDTO<User> user = userServiceClient.getUser(post.getUserId());

        PostDTO postDTO = new PostDTO();
        postDTO.setPost(post);
        if (user != null) {
            postDTO.setUser(user.getData());
        }

        return postDTO;
    }

    /**
     * 新增文章
     * @return 受影响行数
     */
    public int create(Post post) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if (post.getPublishTime() == null) {
            // 默认发布时间为当前时间
            post.setPublishTime(date);
        }

        // 默认时间
        post.setCreateTime(date);
        post.setUpdateTime(date);

        // 入库
        return postMapper.insert(post);
    }

    /**
     * 软删
     */
    public int softDelete(int id) {
        Post post = new Post();
        post.setId(id);
        post.setDeleteTime(new Timestamp(System.currentTimeMillis()));
        return postMapper.softDelete(post);
    }

    /**
     * 物理删
     */
    public int delete(int id) {
        return postMapper.delete(id);
    }
}
