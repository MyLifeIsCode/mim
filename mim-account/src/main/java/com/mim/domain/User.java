package com.mim.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "mim_user")
public class User implements Serializable {
    @Column(name = "id")
    @Id
    private Long uid;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_time")
    private Date createdTime;

}
