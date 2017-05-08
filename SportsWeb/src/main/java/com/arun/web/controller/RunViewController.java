package com.arun.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arun.logging.Loggable;

@Controller
@RequestMapping(value = "/")
public class RunViewController {

	@Loggable
	@RequestMapping(value = {"/","index"})
	public String displayHomePage() {
		return "Select";
	}

	@Loggable
	@RequestMapping("/login")
	public ModelAndView displayLogin() {
      return new ModelAndView("Login");
	}

	@Loggable
	@RequestMapping("/addUser")
	public ModelAndView displayRegisterPage() {
      return new ModelAndView("Register");
	}

	@Loggable
	@RequestMapping("/errorPage")
	public ModelAndView displayErrorPage() {
      return new ModelAndView("Error");
	}

	@Loggable
	@RequestMapping("/selectShoe")
	public ModelAndView displaySelectPage() {
      return new ModelAndView("Select");
	}

	@Loggable
	@RequestMapping("/addShoe")
	public ModelAndView displayAddPage() {
      return new ModelAndView("Add");
	}

	@Loggable
	@RequestMapping("/editShoe")
	public ModelAndView displayEditPage() {
      return new ModelAndView("Edit");
	}

	@Loggable
	@RequestMapping("/manageUser")
	public ModelAndView displayUserPage() {
      return new ModelAndView("User");
	}

	@Loggable
	@RequestMapping("/fileUpload")
	public ModelAndView displayUploadPage() {
      return new ModelAndView("Upload");
	}
	
	@Loggable
	@RequestMapping("/multiFileUpload")
	public ModelAndView displayMultiUploadPage() {
      return new ModelAndView("MultiUpload");
	}

	@Loggable
	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public ModelAndView userLogout(final HttpServletRequest request, final HttpServletResponse response) {
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(null);
		HttpSession httpSession = request.getSession();
		if (null != httpSession) {
			httpSession.removeAttribute("ShoeSession");
			httpSession.invalidate();
		}
		return new ModelAndView("Login");
	}



}