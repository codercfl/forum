package com.hzu.forum.forum.dao;

import com.hzu.forum.forum.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    void insert(User user);

    User findByToken(String token);
}
