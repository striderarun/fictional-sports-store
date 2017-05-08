package com.arun.web.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.arun.logging.Loggable;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	
	@Autowired
	private Environment appProperties;

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
		String targetUrl = appProperties.getProperty("appUrl") + appProperties.getProperty("appBaseUrl");
		setAlwaysUseDefaultTargetUrl(true);
		setTargetUrlParameter(targetUrl);
		handle(request, response, authentication);
	}
}
