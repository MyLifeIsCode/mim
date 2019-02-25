package com.mim.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class User implements Serializable {
    private Long uid;

    private String password;

    private String userName;

    private Date createdTime;

}
