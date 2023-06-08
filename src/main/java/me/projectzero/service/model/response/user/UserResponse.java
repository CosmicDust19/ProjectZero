package me.projectzero.service.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.IdentifiableModel;
import me.projectzero.service.model.response.role.RoleResponse;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements IdentifiableModel<UUID> {

    private UUID id;

    private String username;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Set<RoleResponse> roles;

}
