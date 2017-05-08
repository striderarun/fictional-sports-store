package com.arun.web.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.stereotype.Component;

public class MyAuthenticatedToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final Object principal;

	private final Object credentials;

	public MyAuthenticatedToken(final Object aPrincipal, final Object aCredentials) {
		super(null);
		this.principal = aPrincipal;
		this.credentials = aCredentials;
		setAuthenticated(true);
	}

	public MyAuthenticatedToken(final Object aPrincipal, final Object aCredentials, final Collection<? extends GrantedAuthority> anAuthorities) {
		super(anAuthorities);
		this.principal = aPrincipal;
		this.credentials = aCredentials;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

}
