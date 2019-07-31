package com.mim.enums;

import javax.persistence.AttributeConverter;

public class MessageTypeConvert implements AttributeConverter<MessageType,Integer> {

    @Override
    public Integer convertToDatabaseColumn(MessageType messageType) {
        return messageType.getCode();
    }

    @Override
    public MessageType convertToEntityAttribute(Integer integer) {
        return MessageType.of(integer).orElse(null);
    }
}
