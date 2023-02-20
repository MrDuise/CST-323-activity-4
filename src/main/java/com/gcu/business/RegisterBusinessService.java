package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.CredentialsDataAccessInterface;
import com.gcu.model.UserModel;

public class RegisterBusinessService implements InterfaceRegisterBusinessService {

	@Autowired
	CredentialsDataAccessInterface<UserModel> service;
	
	@Override
	public void registerNewUser(UserModel user) {
		service.register(user);
	}

	
	
}
