package me.projectzero.service.model.response.role;

import me.projectzero.service.common.abstraction.model.IdentifiableModel;
import me.projectzero.service.model.response.privilege.PrivilegeProjection;

import java.util.Collection;

public interface RoleProjection extends IdentifiableModel<Integer> {

    String getName();

    String getDescription();

    Collection<PrivilegeProjection> getPrivileges();

}
