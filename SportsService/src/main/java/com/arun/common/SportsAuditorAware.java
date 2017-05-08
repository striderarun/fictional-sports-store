package com.arun.common;

import org.springframework.data.domain.AuditorAware;

public class SportsAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "mohan";
	}

}
