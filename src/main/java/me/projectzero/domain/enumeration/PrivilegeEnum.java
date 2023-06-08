package me.projectzero.domain.enumeration;

import lombok.NonNull;

public enum PrivilegeEnum {

    BASIC_ACCESS("ROLE_BASIC_ACCESS", "Basic access to use the system"),

    ;

    private final String name;
    private final String description;

    PrivilegeEnum(@NonNull String name, @NonNull String description) {
        this.name = name;
        this.description = description;
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

}
