package com.mim.controller;


import com.mim.domain.SysUser;
import com.mim.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Controller
@RequestMapping("/")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("test")
    @ResponseBody
    public SysUser test(@RequestParam String username){
        return sysUserService.findByUsername(username);
    }
}