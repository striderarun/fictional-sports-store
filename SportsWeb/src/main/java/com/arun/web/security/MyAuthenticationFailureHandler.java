package com.arun.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private Environment appProperties;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
		LOGGER.info("Inside onAuthenticationFailure");
		if (response.getStatus() == 100) {
			response.sendRedirect(appProperties.getProperty("appUrl") + appProperties.getProperty("errorPage.url"));
		} else {
			response.sendRedirect(appProperties.getProperty("appUrl") + appProperties.getProperty("login.url"));
		}
		
	}
}
