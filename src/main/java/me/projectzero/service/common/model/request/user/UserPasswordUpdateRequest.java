package me.projectzero.service.common.model.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.model.interfaces.Model;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordUpdateRequest implements Model {

    @NotEmpty
    private String oldPassword;

    @NotEmpty
    private String newPassword;

}
