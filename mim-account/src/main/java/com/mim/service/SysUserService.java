package com.mim.service;


import com.mim.domain.SysUser;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
public interface SysUserService {
    SysUser findByUsername(String username);
}
