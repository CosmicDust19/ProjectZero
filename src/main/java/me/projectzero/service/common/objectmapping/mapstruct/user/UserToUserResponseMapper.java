package me.projectzero.service.common.objectmapping.mapstruct.user;

import me.projectzero.domain.entity.User;
import me.projectzero.service.common.model.response.user.UserResponse;
import me.projectzero.service.common.objectmapping.interfaces.MapStructMapper;
import me.projectzero.service.common.objectmapping.mapstruct.role.RoleToRoleResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleToRoleResponseMapper.class)
public abstract class UserToUserResponseMapper implements MapStructMapper<User, UserResponse> {
}
