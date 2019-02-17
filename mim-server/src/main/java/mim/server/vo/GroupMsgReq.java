package mim.server.vo;

import lombok.Data;

@Data
public class GroupMsgReq {

    private Long groupId;

    private String msg;
}
