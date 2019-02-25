package com.mim.mapper;


import com.mim.domain.User;
import com.mim.domain.vo.RegisterReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from sys_user")
    User findByUsername(String username);

    @Insert("insert into user values (#{uid},#{userName},#{password},#{createdTime})")
    int register(User user);
}