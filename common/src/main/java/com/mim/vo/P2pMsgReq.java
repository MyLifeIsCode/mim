package com.mim.vo;

import lombok.Data;

@Data
public class P2pMsgReq {

    private Long toUid;

    private String msg;

    private Long fromUid;
}
