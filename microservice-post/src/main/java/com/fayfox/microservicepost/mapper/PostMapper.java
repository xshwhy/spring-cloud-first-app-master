package com.fayfox.microservicepost.mapper;

import com.fayfox.microservicepost.pojo.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
    @Select("SELECT * FROM post WHERE id = #{id}")
    @Results({@Result(property = "category", column = "category_id", one = @One(select = "com.fayfox.microservicepost.mapper.CategoryMapper.findById")),
            @Result(property = "categoryId", column = "category_id")})
    Post findById(int id);

    @InsertProvider(type = PostSqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Post post);

    @UpdateProvider(type = PostSqlProvider.class, method = "softDelete")
    int softDelete(Post post);

    @Delete("DELETE FROM post WHERE id = #{id}")
    int delete(int id);
}
