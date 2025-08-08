package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}

