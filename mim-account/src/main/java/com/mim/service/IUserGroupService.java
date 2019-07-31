package com.mim.service;


import com.mim.domain.user.User;
import com.mim.domain.usergroup.UserGroup;

import java.util.List;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
public interface IUserGroupService {


    List<UserGroup> findByGroupId(Long groupId);

    List<UserGroup> findByUid(Long uid);
}
