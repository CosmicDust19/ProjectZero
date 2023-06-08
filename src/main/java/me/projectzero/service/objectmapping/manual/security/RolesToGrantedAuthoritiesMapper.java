package me.projectzero.service.objectmapping.manual.security;

import me.projectzero.infrastructure.common.util.reflection.ObjectUtils;
import me.projectzero.service.common.abstraction.objectmapping.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RolesToGrantedAuthoritiesMapper implements Mapper<Collection<?>, List<GrantedAuthority>> {

    /**
     * @implSpec The implementation will map using the 'name' and 'privileges' fields of each role element and the 'name' field of each privilege element.
     */
    @Override
    public List<GrantedAuthority> map(Collection<?> roles) {
        if (roles == null) return new ArrayList<>();
        List<GrantedAuthority> destination = new ArrayList<>(roles.size() * 2);
        for (Object role : roles) {
            ObjectUtils.getFieldValue(role, "name", String.class).ifPresent(name -> destination.add(new SimpleGrantedAuthority(name)));
            ObjectUtils.getFieldValue(role, "privileges", Iterable.class).ifPresent(privileges -> {
                for (Object privilege : privileges) {
                    ObjectUtils.getFieldValue(privilege, "name", String.class).ifPresent(name -> destination.add(new SimpleGrantedAuthority(name)));
                }
            });
        }
        return destination;
    }

}
