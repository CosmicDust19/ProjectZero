package me.projectzero.domain.common.enumeration;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum RoleEnum {

    USER("ROLE_USER", "User of the system", PrivilegeEnum.BASIC_ACCESS),

    ;

    private final String name;
    private final String description;
    private final PrivilegeEnum[] privileges;

    RoleEnum(@NonNull String name, @NonNull String description, @NonNull PrivilegeEnum... privileges) {
        this.name = name;
        this.description = description;
        this.privileges = privileges;
    }

    public int getId() {
        return ordinal() + 1;
    }

    public static RoleEnum getById(int id) {
        return RoleEnum.values()[id - 1];
    }

}
