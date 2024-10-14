package com.enablero.todo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String principal;

    public JwtAuthenticationToken(String principal, Object credentials, Collection<?> authorities) {
        super(null);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
