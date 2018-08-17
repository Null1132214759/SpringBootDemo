package com.edu.service.impl;

import com.edu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserService implements UserDetailsService {
    
    @Autowired
    UserServiceImpl userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名从数据库查询对应记录
        User user = userService.login(username);
        if (user == null) {
            throw new UsernameNotFoundException("username is not exists");
        }
        System.out.println("username is : " + user.getUsername() + ", password is :" + user.getPassword());
        return user;
    }
}
