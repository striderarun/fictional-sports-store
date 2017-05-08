package com.arun.security;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.arun.util.SecurityUtil;


public class SecurityFilter implements Filter {

	@Override
	public void init(final FilterConfig iFilterConfig) throws ServletException {
	}

	
	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			if (servletRequest instanceof HttpServletRequest) {
				request = (HttpServletRequest) servletRequest;
				response = (HttpServletResponse) servletResponse;
			} else {
				throw new ServletException("Request should be HTTP");
			}
			if (isValidRequest(request, response)) {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			// Handle exceptions while implementing Exception Handling framework
		}
	}

	
	@SuppressWarnings("unchecked")
	private boolean isValidRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		boolean validRequest = false;

		final String timeStamp = request.getHeader("timestamp");
		final String receivedEncryptedData = request.getHeader("hmac");
		if (StringUtils.isNotBlank(timeStamp) && StringUtils.isNotBlank(receivedEncryptedData)) {
			final long currentTime = Calendar.getInstance().getTimeInMillis();
			final long requestTime = Long.parseLong(timeStamp);
			final long timeDelay = Math.abs(requestTime - currentTime);
			if (timeDelay < 5000) {
				final String expectedEncryptedData = SecurityUtil.calculateRFC2104HMACForString(timeStamp,"82f9f37fd83be96eabb23e15d7548505");
				if (expectedEncryptedData.equals(receivedEncryptedData)) {
					validRequest = true;
				}
			}
		}

		return validRequest;
	}

	
	@Override
	public void destroy() {
	}
}
