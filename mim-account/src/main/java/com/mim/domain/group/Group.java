package com.mim.domain.group;

import com.mim.enums.MessageType;
import com.mim.enums.MessageTypeConvert;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "mim_group")
public class Group implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private Long name;
    @Column(name = "created_at")
    private Date createdAt;

}