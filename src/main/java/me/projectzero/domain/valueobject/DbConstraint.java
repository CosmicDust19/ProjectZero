package me.projectzero.domain.valueobject;

import lombok.experimental.UtilityClass;
import me.projectzero.domain.enumeration.ErrorCode;

import java.util.Locale;

@UtilityClass
public class DbConstraint {

    @UtilityClass
    public class UK {
        public final String USER_USERNAME = "uk_user_username";

        public final String ROLE_NAME = "uk_role_name";

        public final String PRIVILEGE_NAME = "uk_privilege_name";

        public final String USER_ROLE_USER_ROLE = "uk_user_role_user_role";

        public final String ROLE_PRIVILEGE_ROLE_PRIVILEGE = "uk_role_privilege_role_privilege";
    }

    @UtilityClass
    public class FK {
        public final String USER_ROLE_USER = "fk_user_role_user";
        public final String USER_ROLE_ROLE = "fk_user_role_role";

        public final String ROLE_PRIVILEGE_ROLE = "fk_role_privilege_role";
        public final String ROLE_PRIVILEGE_PRIVILEGE = "fk_role_privilege_privilege";
    }

    public ErrorCode getErrorCode(String constraint) {
        if (constraint == null) return ErrorCode.NULL_POINTER;
        return ErrorCode.valueOf(constraint.toUpperCase(Locale.ENGLISH));
    }

}
