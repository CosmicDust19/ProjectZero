package me.projectzero.domain.enumeration;

import lombok.NonNull;

public enum RoleEnum {

    USER("ROLE_USER", "User of the system", PrivilegeEnum.BASIC_ACCESS),

    ;

    private final String name;
    private final String description;
    private final PrivilegeEnum[] privileges;

    RoleEnum(@NonNull String name, @NonNull String description, PrivilegeEnum... privileges) {
        this.name = name;
        this.description = description;
        this.privileges = privileges;
    }

    public int getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PrivilegeEnum[] getPrivileges() {
        return privileges;
    }

}
