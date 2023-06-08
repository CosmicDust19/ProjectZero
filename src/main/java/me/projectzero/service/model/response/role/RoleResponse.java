package me.projectzero.service.model.response.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.IdentifiableModel;
import me.projectzero.service.model.response.privilege.PrivilegeResponse;

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
