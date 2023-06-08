package me.projectzero.service.objectmapping.mapstruct.privilege;

import me.projectzero.domain.entity.Privilege;
import me.projectzero.service.common.abstraction.objectmapping.MapStructMapper;
import me.projectzero.service.model.response.privilege.PrivilegeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PrivilegeToPrivilegeResponseMapper implements MapStructMapper<Privilege, PrivilegeResponse> {
}
