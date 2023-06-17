package me.projectzero.service.common.model.response.privilege;

import me.projectzero.service.common.model.interfaces.IdentifiableModel;

public interface PrivilegeProjection extends IdentifiableModel<Integer> {

    String getName();

    String getDescription();

}
