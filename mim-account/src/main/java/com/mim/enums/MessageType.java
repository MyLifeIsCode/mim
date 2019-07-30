package com.mim.enums;

import com.mim.enumutil.BaseEnum;

import java.util.Optional;

/**
 * @author qll
 * @create 2019-02-15 17:06
 * @desc 聊天消息类型
 **/
public enum MessageType implements BaseEnum<MessageType>{

    PRIVATE_CHAT(1,"readed"),//已读
    GROUP_CHAT(2,"unreaded"),//未读
;
    private Integer code;
    private String name;


    MessageType(int code, String name) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public String getName(){
        return name;
    }
    public static Optional<MessageType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(MessageType.class,code));
    }
}
