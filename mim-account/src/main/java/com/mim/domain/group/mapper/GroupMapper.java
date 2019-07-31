package com.mim.domain.group.mapper;

import com.mim.domain.group.Group;
import com.mim.domain.group.dto.AddGroupReq;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group addGroupToGroup(AddGroupReq addGroupReq);
}
