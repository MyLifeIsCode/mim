package com.mim.service;


import com.mim.domain.group.Group;
import com.mim.domain.user.User;

import java.util.List;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
public interface IGroupService {
    Group findById(Long id);
    Group save(Group group);

    List<Group> findAll();
}
