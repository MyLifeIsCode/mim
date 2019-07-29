package com.mim.enums;

import com.mim.enumutil.BaseEnum;

import java.util.Optional;

/**
 * @author qll
 * @create 2019-02-15 17:06
 * @desc 聊天消息类型
 **/
public enum ChatEnum implements BaseEnum<ChatEnum>{

    PRIVATE_CHAT(1,"ontToOne"),//私聊
    GROUP_CHAT(2,"group"),//群聊
;
    private Integer code;
    private String name;


    ChatEnum(int code,String name) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public String getName(){
        return name;
    }
    public static Optional<ChatEnum> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(ChatEnum.class,code));
    }
}
