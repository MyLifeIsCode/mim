package mim.server.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginRes {

    private Long uid;
    private Date loginDate;
    private String msg;
}
