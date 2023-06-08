package me.projectzero.infrastructure.common.util;

import lombok.experimental.UtilityClass;
import me.projectzero.infrastructure.common.util.reflection.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class SecurityUtils {

    public static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication);
    }

    public static Optional<Object> getPrincipal() {
        return getAuthentication().map(Authentication::getPrincipal);
    }

    public static Optional<String> getUsername() {
        return ObjectUtils.getFieldValue(getPrincipal().orElse(null), "username", String.class);
    }

    public static List<String> getAuthorities() {
        Optional<Authentication> authentication = getAuthentication();
        if (authentication.isEmpty()) return Collections.emptyList();
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority authority : authentication.get().getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return authorities;
    }

}
