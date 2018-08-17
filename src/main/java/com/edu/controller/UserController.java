package com.edu.controller;

import com.edu.entity.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;
    
    /**
     * 用户登录
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("password") String password,
//                        Map<String,Object> map, HttpSession session) {
//        User user = userService.login(username, password);
//        if (user.getUsername() != null) {
//            session.setAttribute("loginUser", username);
//            return "redirect:/main.html";
//        } else {
//            map.put("msg","用户名密码错误");
//            return  "login";
//        }
//    }
}
