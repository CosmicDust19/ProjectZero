package me.projectzero.service.model.response.privilege;

import me.projectzero.service.common.abstraction.model.IdentifiableModel;

public interface PrivilegeProjection extends IdentifiableModel<Integer> {

    String getName();

    String getDescription();

}
