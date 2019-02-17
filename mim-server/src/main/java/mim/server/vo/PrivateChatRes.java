package mim.server.vo;

import lombok.Data;

@Data
public class PrivateChatRes {

    private Long toUid;

    private String msg;
}
