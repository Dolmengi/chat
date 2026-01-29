package com.dolmengi.api.commons.security;

import static java.util.stream.Collectors.toList;

import com.dolmengi.common.domain.security.SessionContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record UserPrincipal(SessionContext sessionContext) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add(sessionContext.role().getAuthority());

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

    @Override
    public String getUsername() {
        return String.valueOf(sessionContext.account().id());
    }

    @Override
    public String getPassword() {
        return null;
    }

}
