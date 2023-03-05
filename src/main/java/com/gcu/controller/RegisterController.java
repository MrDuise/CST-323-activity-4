package com.gcu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.InterfaceRegisterBusinessService;
import com.gcu.model.CredentialsModel;

import com.gcu.model.UserModel;

/**
 * Register controller. Handles displaying the register view handles checking the
 * info once they submit the register form
 * 
 * @author michael, nicole
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    
	// Milestone 4: injected and autowired RegisterBusinessService, and SecurityBusinessService
    
    @Autowired
    private InterfaceRegisterBusinessService service;
    
    Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	/**
	 * Function is called by when the user goes to the URL of '/register/'
	 * @param model
	 * @return register
	 */
	public String display(Model model) {

		model.addAttribute("title", "Register");
		model.addAttribute("userModel", new UserModel());
		return "register";
	}

	/**
	 * called when the user submits the register form
	 * @param userModel - this is passed in from the form, and has the users info binded to the attributes 
	 * @param bindingResult - used for the data validation
	 * @param model
	 * @return login
	 */
	@PostMapping("/doRegister")
	public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		
		logger.info("Entering RegisterController.doRegister()");

		   int userId = 1;

	        // set the ID of the userModel
	        userModel.setUserID(userId);

	        // print out the user for testing purposes
	        System.out.println(userModel.toString());
	        
	        String password = new BCryptPasswordEncoder().encode(userModel.getCredentials().getPassword());
	        
	        userModel.setCredentials(userModel.getCredentials().getUsername(), password);
	        
	        System.out.println(userModel.toString());
	        
	        //register a new user
	        service.registerNewUser(userModel);
		
		//Error handling 
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Register");
			return "register";
		}

    // upon a successful registration the user will be sent back to the login page
		model.addAttribute("title", "Login");
		model.addAttribute("credentialsModel", new CredentialsModel());
		return "login";
	}
}
