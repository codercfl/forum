package com.hzu.forum.forum.service;

import com.hzu.forum.forum.model.User;

public interface UserService {
    User findByToken(String token);

    void insert(User user);
}
