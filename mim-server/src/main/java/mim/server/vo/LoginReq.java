package mim.server.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginReq {

    private Long uid;

    private Date loginTime;


}
