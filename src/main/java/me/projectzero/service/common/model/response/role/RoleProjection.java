package me.projectzero.service.common.model.response.role;

import me.projectzero.service.common.model.interfaces.IdentifiableModel;
import me.projectzero.service.common.model.response.privilege.PrivilegeProjection;

import java.util.Collection;

public interface RoleProjection extends IdentifiableModel<Integer> {

    String getName();

    String getDescription();

    Collection<PrivilegeProjection> getPrivileges();

}
