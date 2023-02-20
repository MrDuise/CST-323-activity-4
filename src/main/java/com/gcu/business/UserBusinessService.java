package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.gcu.data.UsersService;
import com.gcu.model.UserModel;



@Service
public class UserBusinessService implements UserDetailsService {
	
	
	
	@Autowired 
	private UsersService userService;
	
	public UserBusinessService(UsersService service) {
		this.userService = service;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserModel user = userService.findByUserName(username);
		if(user != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			return  new User(user.getCredentials().getUsername(), user.getCredentials().getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}
	
	
	public UserModel findByUserName(String username) throws UsernameNotFoundException {
        UserModel user = userService.findByUserName(username);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("username not found");
        }
	    
	}
	
	//added in milestone 7, get all users from database
	public List<UserModel> getAllUsers(){
	    List<UserModel> usersList = userService.findAllUsers();
		List<UserModel> usersDomain = new ArrayList<UserModel>();
		
		//for loop here to add users to domain
		for(int i = 0; i < usersList.size(); i++) {
			usersDomain.add(new UserModel(usersList.get(i).getUserID(), usersList.get(i).getCredentials(), usersList.get(i).getFirstName(), usersList.get(i).getLastName(), usersList.get(i).getEmail(), usersList.get(i).getPhoneNumber()));
		}
		
		return usersDomain;
	}

	
}
