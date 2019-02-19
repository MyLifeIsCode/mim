package com.common.vo;

import lombok.Data;

@Data
public class GroupMsgReq {
    /**
     * 发送消息
     */
    private String msg;

    /**
     * 发送人id
     */
    private Long fromUid;

    /**
     * 组id
      */
    private Long groupId;


}
