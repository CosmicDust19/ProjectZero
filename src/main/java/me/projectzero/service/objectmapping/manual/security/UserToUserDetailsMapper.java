package me.projectzero.service.objectmapping.manual.security;

import lombok.RequiredArgsConstructor;
import me.projectzero.domain.entity.User;
import me.projectzero.service.common.abstraction.objectmapping.ManualMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToUserDetailsMapper implements ManualMapper<User, org.springframework.security.core.userdetails.User> {

    private final RolesToGrantedAuthoritiesMapper rolesToGrantedAuthoritiesMapper;

    @Override
    public org.springframework.security.core.userdetails.User map(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                rolesToGrantedAuthoritiesMapper.map(user.getRoles())
        );

    }

}
