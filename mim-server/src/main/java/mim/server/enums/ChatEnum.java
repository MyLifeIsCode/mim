package mim.server.enums;

/**
 * @author qll
 * @create 2019-02-15 17:06
 * @desc 聊天消息类型
 **/
public enum ChatEnum {

    PRIVATE_CHAT(1),//私聊
    GROUP_CHAT(2),//群聊
;
    private int type;


    ChatEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
