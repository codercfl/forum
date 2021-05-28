package com.hzu.forum.forum.service.impl;

import com.hzu.forum.forum.dao.UserDao;
import com.hzu.forum.forum.model.User;
import com.hzu.forum.forum.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
