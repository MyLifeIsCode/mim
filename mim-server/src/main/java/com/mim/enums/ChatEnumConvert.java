package com.mim.enums;

import com.mim.enumutil.BaseEnum;

import javax.persistence.AttributeConverter;

public class ChatEnumConvert implements AttributeConverter<ChatEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(ChatEnum chatEnum) {
        return chatEnum.getCode();
    }

    @Override
    public ChatEnum convertToEntityAttribute(Integer integer) {
        return ChatEnum.of(integer).orElse(null);
    }
}
