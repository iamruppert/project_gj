package com.lukasz.project.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    RECRUITER_READ("recruiter:read"),
    RECRUITER_UPDATE("recruiter:update"),
    RECRUITER_CREATE("recruiter:create"),
    RECRUITER_DELETE("recruiter:delete"),

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),


    ;

    private final String permission;
}