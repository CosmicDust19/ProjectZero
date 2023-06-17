package me.projectzero.service.common.model.response.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.model.interfaces.IdentifiableModel;
import me.projectzero.service.common.model.response.privilege.PrivilegeResponse;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse implements IdentifiableModel<Integer> {

    private Integer id;

    private String name;

    private String description;

    private Collection<PrivilegeResponse> privileges;

}
