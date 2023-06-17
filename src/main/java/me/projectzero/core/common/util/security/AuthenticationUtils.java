package me.projectzero.core.common.util.security;

import lombok.experimental.UtilityClass;
import me.projectzero.core.common.util.reflection.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class AuthenticationUtils {

    public Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication);
    }

    public Optional<Object> getPrincipal() {
        return getAuthentication().map(Authentication::getPrincipal);
    }

    public Optional<String> getUsername() {
        return ObjectUtils.getFieldValue(getPrincipal().orElse(null), "username", String.class);
    }

    public List<String> getAuthorities() {
        Optional<Authentication> authentication = getAuthentication();
        if (authentication.isEmpty()) return Collections.emptyList();
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority authority : authentication.get().getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return authorities;
    }

    public void setAuthentication(User userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
