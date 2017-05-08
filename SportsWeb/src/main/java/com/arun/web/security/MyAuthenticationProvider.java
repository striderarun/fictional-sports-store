package com.arun.web.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	@Override
	public Authentication authenticate(final Authentication authentication) {
		Authentication auth = null;
		if (null != authentication) {
			auth = authentication;
			auth.setAuthenticated(true);
		}
		return auth;
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		boolean supportsAuthentication;
		if (null != authentication) {
			supportsAuthentication = true;
		} else {
			supportsAuthentication = false;
		}
		return supportsAuthentication;
	}

}