package com.mim.controller;


import com.mim.domain.group.Group;
import com.mim.domain.group.dto.AddGroupReq;
import com.mim.domain.group.mapper.GroupMapper;
import com.mim.domain.user.User;
import com.mim.domain.user.dto.RegisterReq;
import com.mim.service.IGroupService;
import com.mim.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by  lpw'ASUS on 2018/7/20.
 */
@Controller
@RequestMapping("/group")
@Api(tags = "GroupController")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @RequestMapping("/save")
    @ResponseBody
    @ApiOperation(value = "添加群组")
    public boolean register(@RequestBody AddGroupReq addGroupReq){
        Group group = groupMapper.addGroupToGroup(addGroupReq);
        groupService.save(group);
        return true;
    }
    @RequestMapping("/findAll")
    @ResponseBody
    @ApiOperation(value = "根据id查询")
    public List<Group> findAll(){
        return groupService.findAll();
    }

}