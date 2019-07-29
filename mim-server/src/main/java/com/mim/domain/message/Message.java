package com.mim.domain.message;

import com.mim.enums.ChatEnum;
import com.mim.enums.ChatEnumConvert;
import com.mim.enums.MessageType;
import com.mim.enums.MessageTypeConvert;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mim_message")
public class Message implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "msg")
    private String msg;
    @Column(name = "from_uid")
    private Long fromUid;
    @Column(name = "to_uid")
    private Long toUid;
    @Convert(converter = ChatEnumConvert.class)
    @Column(name = "type")
    private ChatEnum type;
    @Convert(converter = MessageTypeConvert.class)
    @Column(name = "message_type")
    private MessageType messageType;
}
