package ru.bstrdn.voting.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
    USER,
    ADMIN;

    //    https://stackoverflow.com/a/19542316/548473
//    @Override
//    public String getAuthority() {
//        return "ROLE_" + name();
//    }
}