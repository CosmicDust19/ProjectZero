package me.projectzero.domain.entity.auditing.listeners;

import jakarta.persistence.PrePersist;
import me.projectzero.domain.entity.User;
import me.projectzero.domain.common.enumeration.RoleEnum;

/**
 * Listener of the entities of type {@link User}
 */
public class UserListener {

    @PrePersist
    public void prePersist(User target) {
        target.setEnabled(true);
        target.addRoles(RoleEnum.USER);
    }

}
