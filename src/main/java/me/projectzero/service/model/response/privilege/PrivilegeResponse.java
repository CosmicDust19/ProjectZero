package me.projectzero.service.model.response.privilege;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.IdentifiableModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeResponse implements IdentifiableModel<Integer> {

    private Integer id;

    private String name;

    private String description;

}
