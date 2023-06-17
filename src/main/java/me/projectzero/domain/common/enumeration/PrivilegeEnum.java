package me.projectzero.domain.common.enumeration;

import lombok.Getter;
import lombok.NonNull;

@Getter
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
        return ordinal() + 1;
    }

    public static PrivilegeEnum getById(int id) {
        return PrivilegeEnum.values()[id - 1];
    }

}
