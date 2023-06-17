package me.projectzero.service.common.objectmapping.mapstruct.privilege;

import me.projectzero.domain.entity.Privilege;
import me.projectzero.service.common.model.response.privilege.PrivilegeResponse;
import me.projectzero.service.common.objectmapping.interfaces.MapStructMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PrivilegeToPrivilegeResponseMapper implements MapStructMapper<Privilege, PrivilegeResponse> {
}
