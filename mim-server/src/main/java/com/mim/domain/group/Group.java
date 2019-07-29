package com.mim.domain.group;

import com.mim.enums.ChatEnum;
import com.mim.enums.ChatEnumConvert;
import com.mim.enums.MessageType;
import com.mim.enums.MessageTypeConvert;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mim_group")
public class Group implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "uid")
    private Long uid;

    @Convert(converter = MessageTypeConvert.class)
    @Column(name = "message_type")
    private MessageType messageType;
}