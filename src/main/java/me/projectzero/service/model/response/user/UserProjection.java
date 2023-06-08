package me.projectzero.service.model.response.user;

import me.projectzero.service.common.abstraction.model.IdentifiableModel;
import me.projectzero.service.model.response.role.RoleProjection;

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
