package me.projectzero.service.model.request.authentication;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.Model;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Model {

    @NotEmpty(message = "MsgCode.VALIDATION_LOGIN_CUSTOMER_IDENTIFIER_NOT_NULL")
    private String username;

    @NotEmpty(message = "MsgCode.VALIDATION_LOGIN_PASSWORD_NOT_NULL")
    private String password;

}
