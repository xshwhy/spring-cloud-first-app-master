package com.fayfox.microservicepost.mapper;

import com.fayfox.microservicepost.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(int id);
}
