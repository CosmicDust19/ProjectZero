package me.projectzero.service.entitylistener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import me.projectzero.domain.entity.Role;
import me.projectzero.domain.entity.User;
import me.projectzero.domain.enumeration.RoleEnum;

public class UserListener {

    @PrePersist
    public void prePersist(User target) {
        target.addRoles(RoleEnum.USER);
    }

    @PreRemove
    public void preRemove(User target) {
        for (Role role : target.getRoles())
            target.getRoles().remove(role);
    }

}
