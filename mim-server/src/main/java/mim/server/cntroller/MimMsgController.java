package mim.server.cntroller;

import com.common.util.JsonUtils;
import mim.server.service.MsgService;
import mim.server.vo.GroupMsgReq;
import mim.server.vo.P2pMsgReq;
import mim.server.vo.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/mimMsg")
public class MimMsgController {

    @Resource
    private MsgService msgService;


    /**
     * 私聊
     * @param p2pMsgReq
     */
    @PostMapping(value = "/sendP2PMsg")
    public void sendP2PMsg(@RequestBody P2pMsgReq p2pMsgReq){
        Long toUid = p2pMsgReq.getToUid();
        Session session = msgService.getSession(toUid);
        session.sendMsg(JsonUtils.objectToJson(p2pMsgReq));
    }

    /**
     * 群聊
     * @param groupMsgReq
     */
    public void sendGroupMsg(@RequestBody GroupMsgReq groupMsgReq){
        Long groupId = groupMsgReq.getGroupId();


    }

}
