package com.fayfox.microservicepost.mapper;

import com.fayfox.microservicepost.pojo.Post;
import org.apache.ibatis.jdbc.SQL;

public class PostSqlProvider {
    public String insert(Post post) {
        return new SQL() {
            {
                INSERT_INTO("post");
                if (post.getTitle() != null) {
                    VALUES("title", "#{title}");
                }
                if (post.getCategoryId() != 0) {
                    VALUES("category_id", "#{categoryId}");
                }
                if (post.getContent() != null) {
                    VALUES("content", "#{content}");
                }
                VALUES("publish_time", "#{publishTime}");
                VALUES("create_time", "#{createTime}");
                VALUES("update_time", "#{updateTime}");
            }
        }.toString();
    }

    public String softDelete(Post post) {
        return new SQL() {
            {
                UPDATE("post");
                SET("delete_time = #{deleteTime}");
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
