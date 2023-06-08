package me.projectzero.service.objectmapping.mapstruct.user;

import me.projectzero.domain.entity.User;
import me.projectzero.service.common.abstraction.objectmapping.MapStructMapper;
import me.projectzero.service.model.response.user.UserResponse;
import me.projectzero.service.objectmapping.mapstruct.role.RoleToRoleResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleToRoleResponseMapper.class})
public abstract class UserToUserResponseMapper implements MapStructMapper<User, UserResponse> {
}
