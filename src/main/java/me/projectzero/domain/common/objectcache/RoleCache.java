package me.projectzero.domain.common.objectcache;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.projectzero.domain.entity.Role;
import me.projectzero.domain.common.enumeration.RoleEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class RoleCache {

    private final Map<RoleEnum, Role> cache;

    static {
        cache = Arrays.stream(RoleEnum.values())
                .map(role -> Role.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .description(role.getDescription())
                        .privileges(PrivilegeCache.get(role.getPrivileges())).build())
                .collect(Collectors.toUnmodifiableMap(role -> RoleEnum.getById(role.getId()), role -> {
                    role.setDeleted(false);
                    return role;
                }));
    }

    public Role get(RoleEnum key) {
        return get(false, key);
    }

    public Role get(boolean deep, @NonNull RoleEnum key) {
        return new Role(cache.get(key), deep);
    }

    public Set<Role> get(RoleEnum... keys) {
        return get(false, keys);
    }

    public Set<Role> get(boolean deep, @NonNull RoleEnum... keys) {
        Set<Role> roles = new HashSet<>();
        for (RoleEnum key : keys)
            roles.add(new Role(cache.get(key), deep));
        return roles;
    }

    public Set<Role> all() {
        return all(false);
    }

    public Set<Role> all(boolean deep) {
        Set<Role> roles = new HashSet<>();
        for (RoleEnum key : RoleEnum.values())
            roles.add(new Role(cache.get(key), deep));
        return roles;
    }

}
