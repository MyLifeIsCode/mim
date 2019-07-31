package com.mim.domain.usergroup;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "min_user_group")
public class UserGroup {

    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "uid")
    private Long uid;
    @Column(name = "group_id")
    private Long groupId;
//    @Convert(converter = MessageTypeConvert.class)
//    @Column(name = "message_type")
//    private MessageType messageType;
}
