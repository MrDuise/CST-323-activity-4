package com.gcu.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.CredentialsModel;

/**
 * Login controller. Handles displaying the login view handles checking the
 * users info once they submit the login form
 * 
 * @author michael, nicole
 *
 */

@Controller
//@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

	 Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/login")
	//@PostMapping("/")
	/**
	 * Function is called by when the user goes to the URL of '/login/'
	 * 
	 * @param model
	 * @return new ModelAndView
	 */
	public ModelAndView display() {
		
		logger.info("inside the LoginController");
		return new ModelAndView("login", Map.of("title", "Login", "credentialsModel", new CredentialsModel()));
	}


}
