package me.projectzero.service.common.objectmapping.mapstruct.role;

import me.projectzero.domain.entity.Role;
import me.projectzero.service.common.model.response.role.RoleResponse;
import me.projectzero.service.common.objectmapping.interfaces.MapStructMapper;
import me.projectzero.service.common.objectmapping.mapstruct.privilege.PrivilegeToPrivilegeResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PrivilegeToPrivilegeResponseMapper.class)
public abstract class RoleToRoleResponseMapper implements MapStructMapper<Role, RoleResponse> {
}
