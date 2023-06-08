package me.projectzero.service.objectmapping.mapstruct.role;

import me.projectzero.domain.entity.Role;
import me.projectzero.service.common.abstraction.objectmapping.MapStructMapper;
import me.projectzero.service.model.response.role.RoleResponse;
import me.projectzero.service.objectmapping.mapstruct.privilege.PrivilegeToPrivilegeResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PrivilegeToPrivilegeResponseMapper.class)
public abstract class RoleToRoleResponseMapper implements MapStructMapper<Role, RoleResponse> {
}
