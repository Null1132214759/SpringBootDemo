package com.edu.service.impl;

import com.edu.dao.UserMapper;
import com.edu.entity.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
//    @Override
//    public User login(String username, String password) {
//        User user = userMapper.selectUserByUsernameAndPassword(username, password);
//        return user;
//    }


    @Override
    public User login(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
