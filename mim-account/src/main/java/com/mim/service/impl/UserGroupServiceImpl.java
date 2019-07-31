package com.mim.service.impl;


import com.mim.domain.group.Group;
import com.mim.domain.usergroup.UserGroup;
import com.mim.repository.GroupRepository;
import com.mim.repository.UserGroupRepository;
import com.mim.service.IGroupService;
import com.mim.service.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Service
public class UserGroupServiceImpl implements IUserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;


    @Override
    public List<UserGroup> findByGroupId(Long groupId) {
        return userGroupRepository.findByGroupId(groupId);
    }

    @Override
    public List<UserGroup> findByUid(Long uid) {
        return userGroupRepository.findByUid(uid);
    }
}
