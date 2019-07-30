package com.mim.controller;


import com.mim.domain.user.User;
import com.mim.domain.user.dto.RegisterReq;
import com.mim.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Controller
@RequestMapping("/user")
@Api(tags = "UserController")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public List<User> test(@RequestParam String username){
        return userService.findByUsername(username);
    }

    @RequestMapping("/register")
    @ResponseBody
    @ApiOperation(value = "注册用户")
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
    @RequestMapping("/findById/{id}")
    @ResponseBody
    @ApiOperation(value = "根据id查询")
    public User register(@PathVariable("id") Long id){
        return userService.findById(id);
    }

}