package com.gcu.controller;


import java.util.Map;



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


	@GetMapping("/login")
	//@PostMapping("/")
	/**
	 * Function is called by when the user goes to the URL of '/login/'
	 * 
	 * @param model
	 * @return new ModelAndView
	 */
	public ModelAndView display() {

		return new ModelAndView("login", Map.of("title", "Login", "credentialsModel", new CredentialsModel()));
	}


}
