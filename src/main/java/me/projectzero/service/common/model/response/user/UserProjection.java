package me.projectzero.service.common.model.response.user;

import me.projectzero.service.common.model.interfaces.IdentifiableModel;
import me.projectzero.service.common.model.response.role.RoleProjection;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public interface UserProjection extends IdentifiableModel<UUID> {

    String getUsername();

    String getPhoneNumber();

    String getFirstName();

    String getLastName();

    LocalDate getBirthDate();

    Collection<RoleProjection> getRoles();

}
