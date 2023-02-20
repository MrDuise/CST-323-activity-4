package com.gcu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.UserBusinessService;
import com.gcu.model.UserModel;

@RestController
@RequestMapping("/service")
public class UserRestService {
	@Autowired
	UserBusinessService service;
	
	@GetMapping(path="/users")
	public ResponseEntity<?> getUsers() {
		//tries to get all the users from the database
		try {
			List<UserModel> users = service.getAllUsers();
			//if the users list is null meaning no users were in the datbase, return a 404 status code
			if(users == null) 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 else 
				 //return the list of users in json format
				return new ResponseEntity<>(users, HttpStatus.OK);
		}
		//if there is a server error, return a server error status
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
}
