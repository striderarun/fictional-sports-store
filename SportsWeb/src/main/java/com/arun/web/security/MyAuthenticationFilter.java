package com.arun.web.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextImpl;

import com.arun.config.SportsContext;
import com.arun.dto.SessionDTO;
import com.arun.dto.StatusResponseDTO;
import com.arun.logging.Loggable;
import com.arun.web.repository.MySportsRepository;
import com.arun.web.repository.MySportsRepositoryImpl;

//@Component -- Cannot mark as Spring managed because of parameterized constructor; cannot define default constructor for this class
public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements AuthenticationEntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationFilter.class);

//	@Autowired -- cannot autowire as this class is not managed by spring
//	private MySportsRepository mySportsRepository;
	
	public MyAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}
	
	@Override
	protected boolean requiresAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
		boolean isRequiringAuthentication = true;
		LOGGER.info("Request URI:" + request.getRequestURI());
		LOGGER.info("Request URL:" + request.getRequestURL());
		
		final SecurityContext context = SecurityContextHolder.getContext();
		if (null != context.getAuthentication() && null != context.getAuthentication().getPrincipal() && context.getAuthentication().getPrincipal() instanceof SessionDTO) {
			isRequiringAuthentication = false;
		}
		
		LOGGER.info("requiresAuthentication: "+ isRequiringAuthentication);
		return isRequiringAuthentication;
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
		LOGGER.info("Inside attemptAuthentication");
		String userName = request.getParameter("theAccountName");
		String password = request.getParameter("theAccountPW");
		Authentication authentication = null;
		System.out.println(userName + password);
		final HttpSession httpSession = request.getSession(true);
		if (null != userName && null != password) {
			LOGGER.info("User creds");
			MySportsRepositoryImpl mySportsRepository = (MySportsRepositoryImpl)SportsContext.getBean("mySportsRepository");
			StatusResponseDTO statusDTO = mySportsRepository.authenticateUser(userName, password);
			if (statusDTO.getStatusCode() == 0L) {
				LOGGER.info("Valid user");
				SessionDTO sessionDTO = new SessionDTO();
				sessionDTO.setUserName(userName);
				sessionDTO.setPassword(password);
				sessionDTO.setUserRole(statusDTO.getRole());
				httpSession.setAttribute("ShoeSession", sessionDTO);
				
				GrantedAuthority role = new SimpleGrantedAuthority(sessionDTO.getUserRole());
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(role);
				authentication = new MyAuthenticatedToken(sessionDTO,sessionDTO.getPassword(),authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				LOGGER.info("Invalid user");
				response.setStatus(100);
				throw new MyAuthenticationException("Invalid user");
			}
		} else if (null == httpSession) {
			throw new MyAuthenticationException("Session is null");
		} else if (null == authentication) {
			throw new MyAuthenticationException("Authentication is null");
		}
		return authentication;
	}

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
		LOGGER.info("COMMENCE");
	}

}
