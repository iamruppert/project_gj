package com.lukasz.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lukasz.project.model.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN(

            Set.of(ADMIN_CREATE, ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE)
    ),
    RECRUITER(

            Set.of(RECRUITER_CREATE, RECRUITER_READ, RECRUITER_UPDATE, RECRUITER_DELETE)
    ),
    USER(

            Set.of(USER_CREATE, USER_READ, USER_UPDATE, USER_DELETE)
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}