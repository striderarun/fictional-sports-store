package com.arun.web.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.arun.dto.SessionDTO;
import com.arun.logging.Loggable;

@Component
public class RunPermissionEvaluator implements PermissionEvaluator {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	
	
	@Loggable
	@Override
	public boolean hasPermission(final Authentication authentication, final Object targetDomainObject, final Object permission) {
		boolean hasPermission = false;
		if (!hasPermission && canHandle(authentication, permission)) {
			hasPermission = checkPermission(authentication, targetDomainObject, (String) permission);
		} else {
			LOGGER.error("Logged In user does not have access to this resource.");
		}
		return hasPermission;
	}
	
	@Override
	public boolean hasPermission(final Authentication authentication, final Serializable targetId, final String targetType, final Object permission) {
		return false;
	}
	
	/**
	 * @param authentication
	 * @param permission
	 * @return
	 */
	private boolean canHandle(final Authentication authentication, final Object permission) {
		return authentication != null && permission instanceof String;
	}

	/**
	 * @param authentication
	 * @param targetDomainObject
	 * @param permissionKey
	 * @return
	 */
	private boolean checkPermission(final Authentication authentication, final Object targetDomainObject, final String permissionKey) {
		return this.isAllowed(authentication, targetDomainObject, permissionKey);
	}
	
	@Loggable
	public boolean isAllowed(final Authentication authentication, final Object targetDomainObject, final String permission) {
		boolean hasPermission = false;
		if (null != authentication && authentication.getPrincipal() instanceof SessionDTO) {
			SessionDTO sessionDTO = (SessionDTO) authentication.getPrincipal();
			final List<String> permissions = Arrays.asList(StringUtils.split(permission.toLowerCase(), ","));
			
		}
		return hasPermission;
	}
}
