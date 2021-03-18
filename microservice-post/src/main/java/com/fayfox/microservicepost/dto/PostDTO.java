package com.fayfox.microservicepost.dto;

import com.fayfox.microservicepost.pojo.Post;
import com.fayfox.microservicepost.pojo.User;

public class PostDTO {
    private Post post;
    private User user;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
