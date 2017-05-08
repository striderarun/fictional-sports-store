package com.arun.util;

import javax.servlet.http.HttpServletRequest;
import com.arun.dto.SessionDTO;

public class CommonUtil {

	public static SessionDTO getSessionBean(final HttpServletRequest request) {
		SessionDTO sessionDTO = null;
		if (request != null) {
			sessionDTO = (SessionDTO) request.getSession().getAttribute("ShoeSession");
		}
		return sessionDTO;
	}
}
