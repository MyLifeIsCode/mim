package com.mim.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterReq implements Serializable {
    private String userName;
    private String password;
}
