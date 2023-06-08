package me.projectzero.service.model.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.Model;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordUpdateRequest implements Model {

    //@NotEmpty(message = MsgCode.VALIDATION_USER_EMAIL_NOT_EMPTY)
    private String oldPassword;

    //@NotEmpty(message = MsgCode.VALIDATION_USER_PASSWORD_NOT_EMPTY)
    private String newPassword;

}
