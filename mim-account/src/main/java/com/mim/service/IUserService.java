package com.mim.service;


import com.mim.domain.user.User;

import java.util.List;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
public interface IUserService {
    List<User> findByUsername(String username);
    User findById(Long id);
    User register(User user);
}
