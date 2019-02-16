package mim.server.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qll
 * @create 2019-02-13 15:09
 * @desc text数据
 **/
@Data
public class TextData implements Serializable {

    private String msg;

    private int type;

    private String cmd;//login 登录，msg 发送消息

    private long id;


}
