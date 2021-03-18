package com.fayfox.microserviceuser.mapper;

import com.fayfox.microserviceuser.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(int id);
}
