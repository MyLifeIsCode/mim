package com.mim.controller;

import com.mim.vo.UidGroup;
import com.mim.service.AccountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

@Controller
@RequestMapping(value = "/account")
public class AccountController {


    @Resource
    private AccountService accountService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 根据groupid获取uid集合
     * @param groupId
     * @return
     */
    @PostMapping(value = "/getUidsByGroupId/{groupId}")
    @ResponseBody
    public Set<String> getUidsByGroupId(@PathVariable("groupId") Long groupId){
        return accountService.getUidsByGroupId(groupId);
    }

    /**
     * 根据uid获取groupid集合
     * @param uid
     * @return
     */
    @PostMapping(value = "/getGroupIdsByUid/{uid}")
    @ResponseBody
    public Set<String> getGroupIdsByUid(@PathVariable("uid") Long uid){
        return accountService.getGroupsByUid(uid);
    }

    /**
     * 加入到某个群组
     * @param uidGroup
     * @return
     */
    @PostMapping(value = "/addToGroup")
    public void addToGroup(@RequestBody UidGroup uidGroup){
        Long uid = uidGroup.getUid();
        Long groupId = uidGroup.getGroupId();
        accountService.addUidGroupId(uid,groupId);
    }
}



















