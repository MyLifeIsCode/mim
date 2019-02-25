package com.mim.controller;


import com.mim.domain.User;
import com.mim.domain.vo.RegisterReq;
import com.mim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public User test(@RequestParam String username){
        return userService.findByUsername(username);
    }

    @RequestMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody RegisterReq registerReq){
        String userName = registerReq.getUserName();
        String password = registerReq.getPassword();
        User user = new User();
        user.setUid(System.currentTimeMillis());
        user.setUserName(userName);
        user.setPassword(password);
        user.setCreatedTime(new Date());
        userService.register(user);
        return true;
    }
}