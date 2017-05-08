package com.arun.web.security;

import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	
	public MyAuthenticationException(final String msg) {
		super(msg);
	}

	public MyAuthenticationException(final String msg, final Throwable throwable) {
		super(msg, throwable);
	}

}
