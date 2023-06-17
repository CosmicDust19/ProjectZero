package me.projectzero.domain.common.objectcache;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.projectzero.domain.entity.Privilege;
import me.projectzero.domain.common.enumeration.PrivilegeEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class PrivilegeCache {

    private final Map<PrivilegeEnum, Privilege> cache;

    static {
        cache = Arrays.stream(PrivilegeEnum.values())
                .map(privilege -> Privilege.builder()
                        .id(privilege.getId())
                        .name(privilege.getName())
                        .description(privilege.getDescription()).build())
                .collect(Collectors.toUnmodifiableMap(privilege -> PrivilegeEnum.getById(privilege.getId()), privilege -> {
                    privilege.setDeleted(false);
                    return privilege;
                }));
    }

    public Privilege get(@NonNull PrivilegeEnum key) {
        return new Privilege(cache.get(key));
    }

    public Set<Privilege> get(@NonNull PrivilegeEnum... keys) {
        Set<Privilege> privileges = new HashSet<>();
        for (PrivilegeEnum key : keys)
            privileges.add(get(key));
        return privileges;
    }

    public Set<Privilege> all() {
        Set<Privilege> privileges = new HashSet<>();
        for (PrivilegeEnum key : PrivilegeEnum.values())
            privileges.add(get(key));
        return privileges;
    }


}
