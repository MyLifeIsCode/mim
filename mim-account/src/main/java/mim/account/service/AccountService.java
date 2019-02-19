package mim.account.service;

import com.common.util.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class AccountService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 根据uid获取群组
     * @param uid
     * @return
     */
    public Set<String> getGroupsByUid(Long uid){
        return redisTemplate.opsForSet().members(RedisKeyUtil.getGroupUid(uid));
    }

    /**
     * 根据群组id获取uid
     * @param groupId
     * @return
     */
    public Set<String> getUidsByGroupId(Long groupId){
        return redisTemplate.opsForSet().members(RedisKeyUtil.getGroupUid(groupId));
    }

    public void addUidGroupId(Long uid,Long groupId){
        String uidGroup = RedisKeyUtil.getUidGroup(uid);
        String groupUid = RedisKeyUtil.getGroupUid(groupId);
        redisTemplate.opsForSet().add(uidGroup,groupId.toString());
        redisTemplate.opsForSet().add(groupUid,uid.toString());
    }
}
