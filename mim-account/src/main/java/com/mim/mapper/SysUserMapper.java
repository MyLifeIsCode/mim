package com.mim.mapper;


import com.mim.domain.SysUser;

public interface SysUserMapper {
    SysUser findByUsername(String username);
}