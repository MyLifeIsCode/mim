package com.mim.service.impl;


import com.mim.domain.group.Group;
import com.mim.domain.user.User;
import com.mim.repository.GroupRepository;
import com.mim.repository.UserRepository;
import com.mim.service.IGroupService;
import com.mim.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

}
