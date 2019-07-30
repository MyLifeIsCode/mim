package com.mim.service.impl;


import com.mim.domain.user.User;
import com.mim.repository.UserRepository;
import com.mim.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}
