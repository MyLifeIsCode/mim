package com.mim.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginReq {

    private Long uid;

    private Date loginTime;


}
